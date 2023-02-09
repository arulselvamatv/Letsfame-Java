package com.letsfame.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.Subscriptions;
import com.letsfame.repository.WebHooksResponseRepository;
import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
//import com.razorpay.RazorpayClient;
import com.letsfame.response.WebHooksResponse;
import com.letsfame.service.webhookService;
import com.letsfame.webhook.paymentDetailsWebhook;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/Webhooks")
public class WebhookController {

//	@Autowired
//	private RazorpayClient razorpayClient;

	@Autowired
	private webhookService webhookService;

//	@ApiOperation(value = "Allows to fetch payment Details via webhook", response = Response.class)
//	@GetMapping(value = "/getpayment/notification", produces = "application/json")
//	public ResponseEntity<?> getSubscriptions() throws Exception {
//
//		try {
//
//			List<Subscriptions> res = subscriptionService.getsubscriptions();
//			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
//		} catch (Exception e) {
//
//			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}

	@ApiOperation(value = "Create or Update payment event details", response = Response.class)
	@PostMapping(value = "/getpayment", produces = "application/json")
	public ResponseEntity<?> getwebhook(@RequestBody paymentDetailsWebhook notification) throws Exception{

		try {

			Response res = webhookService.getpayment(notification);
			
			return ResponseHandler.successGetResponse("Created successfully.", res, HttpStatus.OK);

		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
