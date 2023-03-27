package com.letsfame.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.response.WebhookEventNotification;
import com.letsfame.service.WebhookService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/txn_api")
public class WebhookController {

	@Autowired
	private WebhookService webhookService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "Create or Update payment event details", response = Response.class)
	@PostMapping(value = "/v1.0/webhook/Notification", produces = "application/json")
	public ResponseEntity<?> webhookPaymentNotifications(@RequestBody WebhookEventNotification notification)
			throws Exception {

		try {

			Response res = webhookService.webhookpaymentNotification(notification);

			return ResponseHandler.successGetResponse("Data Recieved successfully.", res.getData(), res.getHttpStatus());

		} catch (Exception e) {
			logger.error("Error :: webhookPaymentNotifications :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
