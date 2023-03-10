package com.letsfame.service;

import java.util.List;

import com.letsfame.bean.LetsFameSubscription;
import com.letsfame.request.SubscriptionCreateRequest;
import com.letsfame.request.SubscriptionUpgradeAndDowngradeRequest;
import com.razorpay.Subscription;

public interface SubscriptionService {

	Subscription createSubscription(SubscriptionCreateRequest req) throws Exception;

	List<LetsFameSubscription> getsubscriptions(Integer pageNo, Integer pageSize, String sortBy) throws Exception;

	LetsFameSubscription findOne(String id) throws Exception;

	LetsFameSubscription findBySubscriptionsId(String subscriptionsId) throws Exception;

	Subscription cancelSubscription(String subscriptionsId) throws Exception;

	Subscription pauseSubscription(String subscriptionsId) throws Exception;

	Subscription resumeSubscription(String subscriptionsId) throws Exception;

	Subscription subscriptionUpgradeandDowngrade(String subscriptionsId, SubscriptionUpgradeAndDowngradeRequest req)
			throws Exception;

}
