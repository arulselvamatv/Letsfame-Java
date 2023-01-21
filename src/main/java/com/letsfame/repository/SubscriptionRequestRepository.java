package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.SubscriptionRequest;

@Repository
public interface SubscriptionRequestRepository  extends MongoRepository<SubscriptionRequest, String>{

}
