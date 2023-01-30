package com.letsfame.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsfame.bean.PaymentReq;
import com.letsfame.repository.PaymentRepository;
import com.letsfame.response.ResponseDto;
import com.letsfame.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private RazorpayClient razorpay;

	@Override
	public ResponseDto getPaymentDetails(PaymentReq req) {
		ResponseDto response = new ResponseDto();
		List<String> error = new ArrayList<>();
		PaymentReq paymentdetails = new PaymentReq();
		try {

			Payment payment = razorpay.payments.fetch(req.getId());
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
//			paymentdetails.setCreatedAt(payment.get(null));
//			ObjectMapper objectMapper = new ObjectMapper();
//			paymentreq = objectMapper.readValues(payment, PaymentReq.class);
//			paymentstatus.setStatus(paymentRequest.getStatus());
//			statusres = paymentstatus.toString();
			System.out.println(paymentdetails);

			paymentdetails = paymentRepository.save(paymentdetails);

			System.out.println(req.getStatus());

//			if (paymentRequest.getStatus() != null) {
//
//				System.out.println("Paymentdetails done");
//
//				paymentdetails = paymentrepo.save(paymentdetails);
//
//			}

			response.setData(paymentdetails);
			response.setStatus("Success");

//			return new ResponseEntity<>(payment.toString(), HttpStatus.OK);
			// return res;

		} catch (Exception e) {

			response.setStatus("Failed");

			error.add(e.getMessage());
			response.setMessages(error);
			// response.getMessages().add(e.getMessage());

			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));

		}
		return response;
	}

	@Override
	public ResponseDto getAllPaymentDetails() {
		ResponseDto response = new ResponseDto();
		List<String> error = new ArrayList<>();
		List<PaymentReq> savedData = new ArrayList<PaymentReq>();

		try {

			savedData = paymentRepository.findAll();

			response.setData(savedData);
			response.setStatus("Success");
		}

		catch (Exception e) {

			response.setStatus("Failed");

			error.add(e.getMessage());
			response.setMessages(error);
			// response.getMessages().add(e.getMessage());

			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));

		}

		return response;
	}
}
