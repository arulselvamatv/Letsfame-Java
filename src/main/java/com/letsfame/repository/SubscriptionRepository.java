package com.letsfame.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.letsfame.bean.LetsFameSubscription;

@Repository
public interface SubscriptionRepository extends MongoRepository<LetsFameSubscription, String> {

	LetsFameSubscription findBySubscriptionsId(String subscriptionsId);

}
