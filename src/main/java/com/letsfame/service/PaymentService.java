package com.letsfame.service;

import com.letsfame.bean.Payment;
import com.letsfame.dto.PaginationDto;
import com.letsfame.request.PaymentUpdateRequest;

public interface PaymentService {

	Payment updatePaymentDetails(PaymentUpdateRequest req) throws Exception;

	PaginationDto findAllPayment(Integer pageNo, Integer pageSize, String sortBy) throws Exception;

	Payment findByPaymentId(String paymentId) throws Exception;

}
