package com.letsfame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.webhookService;
import com.letsfame.webhook.PaymentDetailsWebhook;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/Webhooks")
public class WebhookController {



	@Autowired
	private webhookService webhookService;

	@ApiOperation(value = "Create or Update payment event details", response = Response.class)
	@PostMapping(value = "/getpayment", produces = "application/json")
	public ResponseEntity<?> getwebhook(@RequestBody PaymentDetailsWebhook notification) throws Exception {

		try {

			Response res = webhookService.getpayment(notification);

			return ResponseHandler.successGetResponse("Created successfully.", res, HttpStatus.OK);

		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
