package com.letsfame.service;

import java.util.List;

import com.letsfame.bean.Payments;
import com.razorpay.Payment;
import com.razorpay.RazorpayException;

public interface PaymentService {

	Payment getPaymentDetails(Payments req) throws RazorpayException;

	List<Payments> getAllPaymentDetails();

}
