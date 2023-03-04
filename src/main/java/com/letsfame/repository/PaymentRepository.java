package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.LetsFamePayment;

@Repository
public interface PaymentRepository extends MongoRepository<LetsFamePayment, String> {
	LetsFamePayment findByPaymentId(String paymentId);
}
