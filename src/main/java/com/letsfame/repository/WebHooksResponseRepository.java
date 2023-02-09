package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.webhook.paymentDetailsWebhook;

@Repository
public interface WebHooksResponseRepository extends MongoRepository<paymentDetailsWebhook, Integer> {

}
