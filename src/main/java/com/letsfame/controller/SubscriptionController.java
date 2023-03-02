package com.letsfame.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.Req.LetsFameSubscriptionReq;
import com.letsfame.Req.LetsFameSubscriptionUpgradeAndDowngradeReq;
import com.letsfame.bean.LetsFameSubscription;
import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.SubscriptionService;
import com.letsfame.util.MessagePropertyService;
import com.razorpay.Subscription;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/txn_api/v1.0/subscription")

public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private MessagePropertyService messageSource;

	@ApiOperation(value = "Create a subscription", response = Response.class)
	@PostMapping(value = "/create", produces = "application/json")
	public ResponseEntity<?> createSubscription(@RequestBody LetsFameSubscriptionReq Req) {

		try {
			Subscription res = subscriptionService.createSubscription(Req);
			return ResponseHandler.successGetResponse("Created successfully.", res.toJson().toMap(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error :: createSubscription :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Allows to fetch subscriptions Details", response = Response.class)
	@GetMapping(value = "/getAll", produces = "application/json")
	public ResponseEntity<?> getSubscriptions() throws Exception {

		try {

			List<LetsFameSubscription> res = subscriptionService.getsubscriptions();
			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error :: getSubscriptions :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Allows to fetch Plan Details", response = Response.class)
	@GetMapping(value = "/getById", produces = "application/json")
	public ResponseEntity<?> getPlanById(@RequestParam String subscriptionsId) throws Exception {

		try {
			LetsFameSubscription res = subscriptionService.findBySubscriptionsId(subscriptionsId);

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error :: getPlanById :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Cancel the Subscription", response = Response.class)
	@PostMapping(value = "/cancel", produces = "application/json")
	public ResponseEntity<?> cancelSubscription(@RequestParam String subscriptionsId) throws Exception {

		try {
			Subscription res = subscriptionService.cancelSubscription(subscriptionsId);

			return ResponseHandler.successGetResponse("Fetched successfully.", res.toJson().toMap(), HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error :: cancelSubscription :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Pause the Subscription", response = Response.class)
	@PostMapping(value = "/pause", produces = "application/json")
	public ResponseEntity<?> pauseSubscription(@RequestParam String subscriptionsId) throws Exception {

		try {
			Subscription res = subscriptionService.pauseSubscription(subscriptionsId);

			return ResponseHandler.successGetResponse("Fetched successfully.", res.toJson().toMap(), HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error :: pauseSubscription :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Resume the Subscription", response = Response.class)
	@PostMapping(value = "/resume", produces = "application/json")
	public ResponseEntity<?> resumeSubscription(@RequestParam String subscriptionsId) throws Exception {

		try {
			Subscription res = subscriptionService.resumeSubscription(subscriptionsId);

			return ResponseHandler.successGetResponse("Fetched successfully.", res.toJson().toMap(), HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error :: resumeSubscription :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Subscription Upgrade And Downgrade", response = Response.class)
	@PostMapping(value = "/{subscriptionsId}", produces = "application/json")
	public ResponseEntity<?> subscriptionUpgradeandDowngrade(@PathVariable String subscriptionsId,
			@RequestBody LetsFameSubscriptionUpgradeAndDowngradeReq req) throws Exception {

		try {
			Subscription res = subscriptionService.subscriptionUpgradeandDowngrade(subscriptionsId, req);

			return ResponseHandler.successGetResponse("Fetched successfully.", res.toJson().toMap(), HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(
					"Error :: subscriptionUpgradeandDowngrade :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
