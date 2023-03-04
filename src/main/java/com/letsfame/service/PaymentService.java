package com.letsfame.service;

import java.util.List;

import com.letsfame.Req.LetsFamePaymentReq;
import com.letsfame.bean.LetsFamePayment;
import com.razorpay.Payment;

public interface PaymentService {

	Payment updatePaymentDetails(LetsFamePaymentReq req) throws Exception;

	List<LetsFamePayment> getAllPaymentDetails() throws Exception;

	LetsFamePayment findByPaymentId(String paymentId) throws Exception;

}
