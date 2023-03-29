package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
	Payment findByPaymentId(String paymentId);

	Payment findByTransactionId(String transactionId);
}
