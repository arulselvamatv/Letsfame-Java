package com.letsfame.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsfame.bean.Payments;
import com.letsfame.repository.PaymentRepository;
import com.letsfame.response.Response;
import com.letsfame.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private RazorpayClient razorpayClient;

	@Override
	public Payment getPaymentDetails(Payments req) throws RazorpayException {
		Response response = new Response();
		List<String> error = new ArrayList<>();
		Payments paymentdetails = new Payments();
		Map<String, Object> message = new HashMap<String, Object>();

		Payment payment = razorpayClient.payments.fetch(req.getId());

		System.out.println("payment:::" + payment);

//		paymentdetails.setId(payment.get("id"));

//		paymentdetails.setOrder_id(payment.get("order_id"));
//		paymentdetails.setBank(payment.get("bank"));
//		paymentdetails.setCurrency(payment.get("currency"));
//		paymentdetails.setContact(payment.get("contact"));
//		paymentdetails.setEmail(payment.get("email"));
//		paymentdetails.setAmount(Double.parseDouble(payment.get("amount").toString()));
//		paymentdetails.setMethod(payment.get("method"));
//		paymentdetails.setStatus(payment.get("status"));
//		paymentdetails.setCreatedAt(payment.get(null));

		paymentdetails.setId(payment.toJson().getString("id"));
		paymentdetails.setCurrency(payment.toJson().getString("currency"));
		paymentdetails.setContact(payment.toJson().getString("contact"));
		paymentdetails.setEmail(payment.toJson().getString("email"));
		paymentdetails.setAmount(Double.parseDouble(payment.toJson().get("amount").toString()));
		paymentdetails.setMethod(payment.toJson().getString("method"));
		paymentdetails.setStatus(payment.toJson().getString("status"));
		paymentdetails.setOrder_id(payment.toJson().getString("order_id"));

		paymentdetails = paymentRepository.save(paymentdetails);

		response.setData(paymentdetails);
		response.setMessage("Success");

		return payment;
	}

	@Override
	public List<Payments> getAllPaymentDetails() {

		return paymentRepository.findAll();

	}
}
