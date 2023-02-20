package com.letsfame.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.dto.PaymentDetailsWebhookDto;
import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.webhookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/txn_api/v1.0/Webhook")
public class WebhookController {

	@Autowired
	private webhookService webhookService;

	@ApiOperation(value = "Create or Update payment event details", response = Response.class)
	@PostMapping(value = "/get/Status", produces = "application/json")
	public ResponseEntity<?> webhookPaymentNotifications(@RequestBody PaymentDetailsWebhookDto notification)
			throws Exception {

		try {

			Response res = webhookService.webhookpaymentNotification(notification);

			return ResponseHandler.successGetResponse("Created successfully.", res, HttpStatus.OK);

		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/getAll/Status")
	@ApiOperation(value = "Get Webhook details", response = Response.class)
	public ResponseEntity<?> webhookAllPaymentNotifications() {

		try {

			List<PaymentDetailsWebhookDto> res = webhookService.getWebhookNotification();

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
