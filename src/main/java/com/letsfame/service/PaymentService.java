package com.letsfame.service;

import com.letsfame.bean.PaymentReq;
import com.letsfame.response.ResponseDto;

public interface PaymentService {
	
	ResponseDto getPaymentDetails(PaymentReq req);
	
	ResponseDto getAllPaymentDetails();

}
