package com.letsfame.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.LetsFamePayment;
import com.letsfame.request.PaymentUpdateRequest;
import com.letsfame.response.Response;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.PaymentService;
import com.razorpay.Payment;

import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping("/txn_api/v1.0/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@ApiOperation(value = "Get / Update payments details", response = Response.class)
	@PostMapping(value = "/fetch", produces = "application/json")
	public ResponseEntity<?> updatepaymentStatus(@RequestBody PaymentUpdateRequest req) throws Exception {

		try {
			Payment res = paymentService.updatePaymentDetails(req);

			return ResponseHandler.successGetResponse("Created successfully.", res.toJson().toMap(), HttpStatus.OK);

		} catch (Exception e) {
			System.out.println("Error :: updatepaymentStatus :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping()
	@ApiOperation(value = "Get payments details", response = Response.class)
	public ResponseEntity<?> getAllPaymentDetails() {

		try {

			List<LetsFamePayment> res = paymentService.findAllPayment();

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error :: getAllPaymentDetails :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(path = "/{paymentId}")
	@ApiOperation(value = "Get payments details", response = Response.class)
	public ResponseEntity<?> getPaymentDetailsById(@PathVariable String paymentId) {

		try {

			LetsFamePayment res = paymentService.findByPaymentId(paymentId);

			System.out.println("paymet" + res);

			return ResponseHandler.successGetResponse("Fetched successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error :: getPaymentDetailsById :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
