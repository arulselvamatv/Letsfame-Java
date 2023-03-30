package com.letsfame.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.Subscription;
import com.letsfame.dto.PaginationDto;
import com.letsfame.request.SubscriptionCreateRequest;
import com.letsfame.request.SubscriptionUpdateRequest;
import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.SubscriptionService;

import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping("/txn_api")

public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "Create a subscription", response = Response.class)
	@PostMapping(value = "/v1.0/subscription", produces = "application/json")
	public ResponseEntity<?> createSubscription(@RequestBody SubscriptionCreateRequest Req) {

		try {
			Subscription res = subscriptionService.createSubscription(Req);
			return ResponseHandler.successGetResponse("Created successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error :: createSubscription :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Get All subscriptions Details", response = Response.class)
	@GetMapping(value = "/v1.0/subscription", produces = "application/json")
	public ResponseEntity<?> findAllSubscriptions(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy)
			throws Exception {

		try {

			PaginationDto res = subscriptionService.findAllsubscriptions(pageNumber, pageSize, sortBy);
			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error :: findAllSubscriptions :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Get Subscription Details By Id", response = Response.class)
	@GetMapping(value = "/v1.0/{subscription_id}", produces = "application/json")
	public ResponseEntity<?> findBySubscriptionId(@PathVariable(name = "subscription_id") String subscriptionsId)
			throws Exception {

		try {
			Subscription res = subscriptionService.findBySubscriptionsId(subscriptionsId);

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error :: findBySubscriptionId :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Subscription Update", response = Response.class)
	@PutMapping(value = "/v1.0/{subscription_id}/{action}", produces = "application/json")
	public ResponseEntity<?> subscriptionUpdate(@PathVariable String action,
			@PathVariable(name = "subscription_id") String subscriptionsId) throws Exception {

		try {
			Subscription res = null;

			if (("cancel").equalsIgnoreCase(action)) {
				res = subscriptionService.cancelSubscription(subscriptionsId);
			} else if (("pause").equalsIgnoreCase(action)) {
				res = subscriptionService.pauseSubscription(subscriptionsId);
			} else if (("resume").equalsIgnoreCase(action)) {
				res = subscriptionService.resumeSubscription(subscriptionsId);
			}
			return ResponseHandler.successGetResponse("updated successfully.", res, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error :: subscriptionUpdate :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Subscription Update", response = Response.class)
	@PutMapping(value = "/v1.0/{subscription_id}", produces = "application/json")
	public ResponseEntity<?> subscriptionUpgradeandDowngrade(
			@PathVariable(name = "subscription_id") String subscriptionsId, @RequestBody SubscriptionUpdateRequest req)
			throws Exception {

		try {
			Subscription res = subscriptionService.subscriptionUpgradeandDowngrade(subscriptionsId, req);

			return ResponseHandler.successGetResponse("updated successfully.", res, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error :: subscriptionUpgradeandDowngrade :: Exception ::{} ",
					ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "To get subscription Details by Memeber Id", response = Response.class)
	@GetMapping(value = "/v1.0/{member_id}", produces = "application/json")
	public ResponseEntity<?> findSubscriptionByMemberId(@RequestParam(name = "member_id") String memberId)
			throws Exception {

		try {
			Subscription res = subscriptionService.findSubscriptionByMemberId(memberId);

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error :: findSubscriptionByMemberId :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
