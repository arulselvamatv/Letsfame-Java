package com.letsfame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.Payments;
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

	@ApiOperation(value = "Update payments details", response = Response.class)
	@PostMapping(value = "/Update", produces = "application/json")
	public ResponseEntity<?> paymentsDetails(@RequestBody Payments req) throws Exception {

		try {

			Payment res = paymentService.getPaymentDetails(req);

			return ResponseHandler.successGetResponse("Created successfully.", res.toJson().toMap(), HttpStatus.OK);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
//
//	@GetMapping(path = "/txn_api/v1.0/fetchAllPayments")
//	@ApiOperation(value = "getall Payment Details")
//	public ResponseEntity<?> payments() {
//
//		try {
//
//			ResponseDto res = paymentService.getAllPaymentDetails();
//
//			return ResponseHandler.successGetResponse("Created successfully.", res,HttpStatus.OK);
//		} catch (Exception e) {
//
//			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}
//
//}
