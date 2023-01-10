package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.letsfame.bean.SubscriptionRequest;

public interface SubscriptionRequestRepository  extends MongoRepository<SubscriptionRequest, String>{

}
