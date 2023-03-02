package com.letsfame.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.LetsFamePayment;

@Repository
public interface PaymentRepository extends MongoRepository<LetsFamePayment, String> {
	Optional<LetsFamePayment> findByPaymentId(String paymentId);
}
