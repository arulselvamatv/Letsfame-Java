package com.letsfame.service;

import com.letsfame.bean.Payments;
import com.letsfame.response.Response;

public interface PaymentService {

	Response getPaymentDetails(Payments req);

//	Response getAllPaymentDetails();

}
