package com.letsfame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.PaymentReq;
import com.letsfame.response.ResponseDto;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.PaymentService;

import io.swagger.annotations.ApiOperation;

@RestController()
public class PaymentController {

//	@Autowired
//	private RazorpayClient razorpay;

	@Autowired
	private PaymentService paymentService;

	@PostMapping(path = "/txn_api/v1.0/paymentDetails")
	@ApiOperation(value = "Update Payment Details")
	public ResponseEntity<?> paymentsDetails(@RequestBody PaymentReq req) {

		try {

			ResponseDto res = paymentService.getPaymentDetails(req);

			return ResponseHandler.generateResponse(HttpStatus.OK, res);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/txn_api/v1.0/fetchAllPayments")
	@ApiOperation(value = "getall Payment Details")
	public ResponseEntity<?> payments() {

		try {

			ResponseDto res = paymentService.getAllPaymentDetails();

			return ResponseHandler.generateResponse(HttpStatus.OK, res);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
