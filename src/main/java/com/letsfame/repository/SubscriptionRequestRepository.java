package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.Subscriptions;
import com.razorpay.Subscription;

@Repository
public interface SubscriptionRequestRepository  extends MongoRepository<Subscriptions, String>{

	void save(Subscription subscription);

}
