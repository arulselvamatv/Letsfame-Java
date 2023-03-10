package com.letsfame.service;

import java.util.List;

import com.letsfame.bean.LetsFamePayment;
import com.letsfame.request.PaymentUpdateRequest;
import com.razorpay.Payment;

public interface PaymentService {

	Payment updatePaymentDetails(PaymentUpdateRequest req) throws Exception;

	List<LetsFamePayment> findAllPayment() throws Exception;

	LetsFamePayment findByPaymentId(String paymentId) throws Exception;

}
