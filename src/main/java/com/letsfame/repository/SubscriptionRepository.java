package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.Subscription;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

	Subscription findBySubscriptionsId(String subscriptionsId);

	Subscription findByMemberId(String memberId);
}
