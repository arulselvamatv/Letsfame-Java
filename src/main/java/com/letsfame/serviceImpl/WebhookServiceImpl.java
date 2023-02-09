package com.letsfame.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.letsfame.repository.WebHooksResponseRepository;
import com.letsfame.response.Response;
import com.letsfame.service.webhookService;
import com.letsfame.webhook.paymentDetailsWebhook;

@Service
@Transactional
public class WebhookServiceImpl implements webhookService {

	@Autowired
	private WebHooksResponseRepository webHooksResponseRepository;

	@Value("com.letsfame.serviceImpl.WebhookServiceImpl.username")
	private String username;
	@Value("com.letsfame.serviceImpl.WebhookServiceImpl.password")
	private String password;
	@Value("com.letsfame.serviceImpl.WebhookServiceImpl.url")
	private String url;

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public Response getpayment(paymentDetailsWebhook notification) {

		Response response = new Response();
		paymentDetailsWebhook savedData1 = new paymentDetailsWebhook();
		List<String> error = new ArrayList<>();
		System.out.println("webhook ::" + notification);
		try {

			savedData1 = webHooksResponseRepository.save(notification);

			HttpHeaders headers = new HttpHeaders();
			headers.setBasicAuth(username, password);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<paymentDetailsWebhook> entity = new HttpEntity<paymentDetailsWebhook>(notification, headers);

			paymentDetailsWebhook res = restTemplate.exchange(url
					+ "/api/swagger-ui/index.html?configUrl=/api/v3/api-docs/swagger-config#/Subscription/updateSubscription",
					HttpMethod.PUT, entity, paymentDetailsWebhook.class).getBody();

			response.setData(savedData1);
			response.setMessage("Success");

		} catch (Exception e) {

			response.setMessage("Failed");
			error.add(e.getMessage());
//			response.setMessages(error);
			// response.getMessages().add(e.getMessage());
			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));

		}
		return response;
	}
}
