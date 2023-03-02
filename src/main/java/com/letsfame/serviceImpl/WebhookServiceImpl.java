package com.letsfame.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.letsfame.bean.LetsFamePayment;
import com.letsfame.bean.LetsFameSubscription;
import com.letsfame.bean.WebhookPaymentDetails;
import com.letsfame.bean.WebhookPaymentStatusByMember;
import com.letsfame.dto.WebhookPaymentStatusByMemberDto;
import com.letsfame.repository.WebhooksRepository;
import com.letsfame.response.Response;
import com.letsfame.service.PaymentService;
import com.letsfame.service.SubscriptionService;
import com.letsfame.service.WebhookService;
import com.razorpay.Invoice;
import com.razorpay.RazorpayClient;

@Service
@Transactional
public class WebhookServiceImpl implements WebhookService {

	@Autowired
	private WebhooksRepository webHooksRepository;

	@Value("${com.letsfame.serviceImpl.WebhookServiceImpl.username}")
	private String username;
	@Value("${com.letsfame.serviceImpl.WebhookServiceImpl.password}")
	private String password;
	@Value("${com.letsfame.serviceImpl.WebhookServiceImpl.url}")
	private String url;
	@Autowired
	private RazorpayClient razorpayClient;

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private PaymentService paymentService;

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public Response webhookpaymentNotification(WebhookPaymentDetails notification) {

		Response response = new Response();
		if (!"payment.captured".equalsIgnoreCase(notification.getEvent().getEvent())) {
			return response;
		}

		WebhookPaymentDetails savedData1 = new WebhookPaymentDetails();
		WebhookPaymentStatusByMemberDto savedData2 = new WebhookPaymentStatusByMemberDto();
		WebhookPaymentStatusByMember paymentstatus = new WebhookPaymentStatusByMember();

		List<String> error = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("webhook ::" + notification);
		try {

			// To get invoice details for find subscription ID
			Invoice invoice = razorpayClient.invoices
					.fetch(notification.getEvent().getPayload().getPayment().getEntity().getInvoiceId());

			System.out.println("invoice_details::" + invoice);

			System.out.println("invoice::" + invoice.get("subscription_id"));
			notification.setSubscriptionId(invoice.get("subscription_id"));

			LetsFamePayment payments = paymentService
					.findByPaymentId(notification.getEvent().getPayload().getPayment().getEntity().getId());

			// To save payments status
			if (payments != null) {
				savedData1 = webHooksRepository.save(notification);
				System.out.println("Payments status updated Sucessfully ::" + savedData1);
				// To share data to member API

				paymentstatus.setRazorCustomerId(
						savedData1.getEvent().getPayload().getPayment().getEntity().getCustomerId());
				paymentstatus.setSubscriptionId(notification.getSubscriptionId());
				paymentstatus.setPaymentId(savedData1.getEvent().getPayload().getPayment().getEntity().getId());
				// To get Member ID and Audit Files
				LetsFameSubscription subscription = subscriptionService
						.findBySubscriptionsId(notification.getSubscriptionId());
				System.out.println("subscriptionID::" + subscription);
				String memberId = subscription.getMemberId();
				paymentstatus.setExpiredAt(subscription.getExpire_by());
				paymentstatus.setSubscribedAt(subscription.getStart_at());

				System.out.println("expire::" + subscription.getExpire_by());
				System.out.println("created::" + subscription.getCreated_at());
				// Member API Connecting

				HttpHeaders headers = new HttpHeaders();
				headers.setBasicAuth(username, password);
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

				HttpEntity<WebhookPaymentStatusByMember> entity = new HttpEntity<WebhookPaymentStatusByMember>(
						paymentstatus, headers);
				String fullUrl = url + "/api/v1.0/member/" + memberId + "/subscription";
				System.out.println("Full URL::" + fullUrl);

				ResponseEntity<String> res = restTemplate.exchange(fullUrl, HttpMethod.PUT, entity, String.class);

				System.out.println("res:::" + res);

				response.setData(new JSONObject(res.getBody()).toMap());
				response.setHttpStatus(HttpStatus.OK);

			} else {
				System.out.println("Payments status is null ::" + savedData1);

			}
		} catch (Exception e) {

			response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			error.add(e.getMessage());
//			response.setMessages(error);
			// response.getMessages().add(e.getMessage());
			System.out.println("Error :: webhookpaymentNotification :: Exception::" + ExceptionUtils.getStackTrace(e));

		}
		return response;
	}

	@Override
	public List<WebhookPaymentDetails> getWebhookNotification() {

		return webHooksRepository.findAll();

	}

}
