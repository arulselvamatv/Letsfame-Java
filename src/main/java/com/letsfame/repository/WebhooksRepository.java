package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.WebhookPaymentDetails;
import com.letsfame.dto.WebhookPaymentDetailsDto;

@Repository
public interface WebhooksRepository extends MongoRepository<WebhookPaymentDetails, Integer> {

}
