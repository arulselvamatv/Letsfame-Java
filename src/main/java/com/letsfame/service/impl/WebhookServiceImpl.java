package com.letsfame.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

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

import com.letsfame.bean.Payment;
import com.letsfame.bean.Subscription;
import com.letsfame.repository.PaymentRepository;
import com.letsfame.response.MemberPaymentUpdateRequest;
import com.letsfame.response.Response;
import com.letsfame.response.WebhookEventNotification;
import com.letsfame.service.PaymentService;
import com.letsfame.service.SubscriptionService;
import com.letsfame.service.WebhookService;
import com.letsfame.util.DateUtils;
import com.razorpay.Invoice;
import com.razorpay.RazorpayClient;

@Service
@Transactional
public class WebhookServiceImpl implements WebhookService {

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

	@Autowired
	private PaymentRepository paymentRepository;

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public Response webhookpaymentNotification(WebhookEventNotification notification) throws Exception {

		Response response = new Response();
		if (!"payment.captured".equalsIgnoreCase(notification.getEvent().getEvent())) {
			return response;
		}

		MemberPaymentUpdateRequest paymentstatus = new MemberPaymentUpdateRequest();

		System.out.println("webhook ::" + notification);

		
		// To get invoice details for find subscription ID
		Invoice invoice = razorpayClient.invoices
				.fetch(notification.getEvent().getPayload().getPayment().getEntity().getInvoice_id());

		System.out.println("invoice_details::" + invoice);

		System.out.println("invoice_SubscriptionId::" + invoice.get("subscription_id"));
		notification.setSubscriptionId(invoice.get("subscription_id"));
		

		Payment findByPayment = paymentService
				.findByPaymentId(notification.getEvent().getPayload().getPayment().getEntity().getPaymentId());
		System.out.println("findByPayment ::" + findByPayment);

		// To save process of payments status if new payment

		if (findByPayment == null) {
			System.out.println("Payments status updated Sucessfully ::" + findByPayment);

			com.razorpay.Payment payment = razorpayClient.payments
					.fetch(notification.getEvent().getPayload().getPayment().getEntity().getPaymentId());

			Payment responseData = webhookPaymentToLetsFamePayment(payment);
			paymentRepository.save(responseData);
		}

		// To share data to member API

		paymentstatus
				.setRazor_customer_id(notification.getEvent().getPayload().getPayment().getEntity().getCustomer_id());

		paymentstatus.setMember_subscription_id(notification.getSubscriptionId());

		paymentstatus.setPayment_id(notification.getEvent().getPayload().getPayment().getEntity().getPaymentId());

		
		// To get Member ID
		Subscription subscription = subscriptionService.findBySubscriptionsId(notification.getSubscriptionId());
		System.out.println("subscriptionID::" + subscription);
		String memberId = subscription.getMemberId();
		

		// To get Subscription Details for Subscription start Date & Expired Date

		com.razorpay.Subscription updatedSubscription = razorpayClient.subscriptions
				.fetch(notification.getSubscriptionId());

		System.out.println("updateSubscription::" + updatedSubscription);

		Date expire = DateUtils.getRazorPayTimeStamp(updatedSubscription.get("current_end"));
		Date created = DateUtils.getRazorPayTimeStamp(updatedSubscription.get("current_start"));

		String LocalDate = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'";

		SimpleDateFormat dateFormat = new SimpleDateFormat(LocalDate);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		String ExpireDate = dateFormat.format(expire);
		String SubcribeAtDate = dateFormat.format(created);

		paymentstatus.setExpired_at(ExpireDate);
		paymentstatus.setSubscribed_at(SubcribeAtDate);


		
		// To get Plan Details for get plan name and plan frequency

		com.razorpay.Plan getPlanDetails = razorpayClient.plans.fetch(updatedSubscription.get("plan_id"));

		System.out.println("planDetails::" + getPlanDetails);

		JSONObject item = getPlanDetails.get("item");

		paymentstatus.setSubscription_package(item.getString("name"));;
		paymentstatus.setPayment_frequency(getPlanDetails.get("period"));
		

		// Member API Connecting

		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth(username, password);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<MemberPaymentUpdateRequest> entity = new HttpEntity<MemberPaymentUpdateRequest>(paymentstatus,
				headers);
		String fullUrl = url + "/api/v1.0/member/" + memberId + "/memberSubscription";
		System.out.println("Full URL::" + fullUrl);

		ResponseEntity<String> res = restTemplate.exchange(fullUrl, HttpMethod.PUT, entity, String.class);

		System.out.println("res:::" + res);

		response.setData(new JSONObject(res.getBody()).toMap());
		response.setHttpStatus(HttpStatus.OK);

		return response;

	}

	private Payment webhookPaymentToLetsFamePayment(com.razorpay.Payment payment) {

		Payment paymentData = new Payment();

		JSONObject paymentJsonObject = payment.toJson();

		System.out.println("payment:::" + paymentJsonObject);

		paymentData.setPaymentId(paymentJsonObject.getString("id"));
		paymentData.setEntity(paymentJsonObject.getString("entity"));
		paymentData.setAmount(paymentJsonObject.getDouble("amount"));
		paymentData.setCurrency(paymentJsonObject.getString("currency"));
		paymentData.setOrderId(paymentJsonObject.getString("order_id"));
		paymentData.setInvoiceId(paymentJsonObject.getString("invoice_id"));
		paymentData.setInternational(paymentJsonObject.getBoolean("international"));
		paymentData.setMethod(paymentJsonObject.getString("method"));
		paymentData.setAmount_refunded(paymentJsonObject.getDouble("amount_refunded"));
		paymentData.setCaptured(paymentJsonObject.getBoolean("captured"));
		if (!paymentJsonObject.isNull("description")) {
			paymentData.setDescription(paymentJsonObject.getString("description"));
		}
		if (!paymentJsonObject.isNull("card_id")) {
			paymentData.setCardId(paymentJsonObject.getString("card_id"));
		}
		if (!paymentJsonObject.isNull("bank")) {
			paymentData.setBank(paymentJsonObject.getString("bank"));
		}
		if (!paymentJsonObject.isNull("wallet")) {
			paymentData.setWallet(paymentJsonObject.getString("wallet"));
		}
		paymentData.setVpa(paymentJsonObject.getString("vpa"));
		paymentData.setEmail(paymentJsonObject.getString("email"));
		paymentData.setContact(paymentJsonObject.getString("contact"));
		paymentData.setCustomerId(paymentJsonObject.getString("customer_id"));
		paymentData.setTokenId(paymentJsonObject.getString("token_id"));

		if (!paymentJsonObject.isNull("created_at")) {
			paymentData.setCreatedAt(DateUtils.getRazorPayTimeStamp(paymentJsonObject.getInt("created_at")));
		}

		return paymentData;

	}

}
