package com.letsfame.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.letsfame.bean.SubscriptionRequest;
import com.letsfame.bean.SubscriptionRequestDto;
import com.letsfame.repository.SubscriptionRequestRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Subscription;

@RestController()
public class SubscriptionController {

	@Autowired
	private RazorpayClient razorpayClient;

	@Autowired
	private SubscriptionRequestRepository subscriptionRequestRepository;
	
	@PostMapping(path = "/create/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> subscription(@RequestBody final SubscriptionRequest subscriptionRequest) {

		JSONObject message = new JSONObject();
		SubscriptionRequestDto s1 = new SubscriptionRequestDto();
		
		try {
			System.out.println(subscriptionRequest);
			int startAt = (int) (subscriptionRequest.getStartAt().getTime()/1000);
			int expireBy = (int) (subscriptionRequest.getExpireBy().getTime()/1000);
//			SubscriptionRequest ss = new SubscriptionRequest();
			s1.setAddons(subscriptionRequest.getAddons());
			s1.setCustomerNotify(subscriptionRequest.getCustomerNotify());
			s1.setExpireBy(expireBy);
			s1.setStartAt(startAt);
			s1.setPlanId(subscriptionRequest.getPlanId());
			s1.setQuantity(subscriptionRequest.getQuantity());
			s1.setTotalCount(subscriptionRequest.getTotalCount());
			JSONObject jsonObject = s1.toJsonObject();
			System.out.println("This is json object::" + jsonObject);
			Subscription subscription = razorpayClient.subscriptions.create(jsonObject);
			System.out.println(subscription);
			message = subscription.toJson();
			System.out.println(message);
			if(subscription!=null) {
				subscriptionRequestRepository.save(subscriptionRequest);
			}

		} catch (RazorpayException e) {

			// Handle Exception
			message.put("error", ExceptionUtils.getStackTrace(e));
			System.out.println(e.getMessage());

		}
		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}

	@GetMapping(path = "/FetchAllSubscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> plan() {

		JSONObject message = new JSONObject();
		try {

			List<Subscription> subscription = razorpayClient.subscriptions.fetchAll();

			return new ResponseEntity<>(subscription.toString(), HttpStatus.OK);

		} catch (RazorpayException e) {

			// Handle Exception
			message.put("error", e.getMessage());
			System.out.println(e.getMessage());

		}
		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}
}
