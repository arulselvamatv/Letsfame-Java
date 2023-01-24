package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.PaymentReq;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentReq, String> {

}
