package com.letsfame.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsfame.bean.OrderRequest;
import com.letsfame.bean.PaymentReq;
import com.letsfame.repository.PaymentRepository;
import com.letsfame.response.ResponseDto;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController()
public class PaymentController {

	@Autowired
	private RazorpayClient razorpay;

	@Autowired
	private PaymentRepository paymentrepo;

	@PostMapping(path = "/txn_api/v1.0/fetchPayment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> Oneplan(@RequestBody final PaymentReq paymentRequest) {

		JSONObject message = new JSONObject();
		PaymentReq paymentdetails = new PaymentReq();

		ResponseDto res = new ResponseDto();

		try {

			Payment payment = razorpay.payments.fetch(paymentRequest.getId());
			System.out.println("payment:::" + payment);

			paymentdetails.setId(payment.get("id"));
			paymentdetails.setOrder_id(payment.get("order_id"));
			paymentdetails.setBank(payment.get("bank"));
			paymentdetails.setCurrency(payment.get("currency"));
			paymentdetails.setContact(payment.get("contact"));
			paymentdetails.setEmail(payment.get("email"));
//			paymentdetails.setAmount(payment.get("amount"));
			paymentdetails.setAmount(Double.parseDouble(payment.get("amount").toString()));
			paymentdetails.setMethod(payment.get("method"));
			paymentdetails.setStatus(payment.get("status"));

//			ObjectMapper objectMapper = new ObjectMapper();
//			paymentreq = objectMapper.readValues(payment, PaymentReq.class);
//			paymentstatus.setStatus(paymentRequest.getStatus());
//			statusres = paymentstatus.toString();
			System.out.println(paymentdetails);
			paymentdetails = paymentrepo.save(paymentdetails);

			System.out.println(paymentRequest.getStatus());

//			if (paymentRequest.getStatus() != null) {
//
//				System.out.println("Paymentdetails done");
//
//				paymentdetails = paymentrepo.save(paymentdetails);
//
//			}

			res.setData(paymentdetails);
			res.setStatus("Success");

//			return new ResponseEntity<>(payment.toString(), HttpStatus.OK);

		} catch (Exception e) {
			res.setStatus("Failed");
			res.setMessage(e.getMessage());
			System.out.println("Error while order :: Exception::" + ExceptionUtils.getStackTrace(e));

		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping(path = "/txn_api/v1.0/fetchAllPayments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> payment() {

		JSONObject message = new JSONObject();

		try {

			List<Payment> payment = razorpay.payments.fetchAll();

			return new ResponseEntity<>(payment.toString(), HttpStatus.OK);

		} catch (RazorpayException e) {

			// Handle Exception
			message.put("error", e.getMessage());
			System.out.println(e.getMessage());

		}
		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}

}
