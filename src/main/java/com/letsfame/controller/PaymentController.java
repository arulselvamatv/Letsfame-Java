package com.letsfame.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.Payment;
import com.letsfame.dto.PaginationDto;
import com.letsfame.request.PaymentUpdateRequest;
import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.PaymentService;

import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping("/txn_api")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "Get / Update payments details", response = Response.class)
	@PutMapping(value = "/v1.0/payments", produces = "application/json")
	public ResponseEntity<?> updatepaymentDetails(@RequestBody PaymentUpdateRequest req) throws Exception {

		try {
			Payment res = paymentService.updatePaymentDetails(req);

			return ResponseHandler.successGetResponse("Created successfully.", res, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error :: updatepaymentDetails :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/v1.0/payments")
	@ApiOperation(value = "Get payments details", response = Response.class)
	public ResponseEntity<?> findAllPayment(@RequestParam(defaultValue = "0") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy)
			throws Exception {

		try {

			PaginationDto res = paymentService.findAllPayment(pageNumber, pageSize, sortBy);

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error :: findAllPayment :: Exception ::{} ", ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/v1.0/{paymentId}")
	@ApiOperation(value = "Get payments details", response = Response.class)
	public ResponseEntity<?> findByPaymentDetailsById(@PathVariable String paymentId) throws Exception {

		try {

			Payment res = paymentService.findByPaymentId(paymentId);

			System.out.println("paymet" + res);

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error :: findByPaymentDetailsById :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
