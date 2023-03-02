package com.letsfame.service;

import java.util.List;

import com.letsfame.Req.LetsFameSubscriptionReq;
import com.letsfame.Req.LetsFameSubscriptionUpgradeAndDowngradeReq;
import com.letsfame.bean.LetsFameSubscription;
import com.razorpay.Subscription;

public interface SubscriptionService {

	Subscription createSubscription(LetsFameSubscriptionReq req) throws Exception;

	List<LetsFameSubscription> getsubscriptions() throws Exception;

	LetsFameSubscription findOne(String id) throws Exception;

	LetsFameSubscription findBySubscriptionsId(String subscriptionsId) throws Exception;

	Subscription cancelSubscription(String subscriptionsId) throws Exception;

	Subscription pauseSubscription(String subscriptionsId) throws Exception;

	Subscription resumeSubscription(String subscriptionsId) throws Exception;

	Subscription subscriptionUpgradeandDowngrade(String subscriptionsId, LetsFameSubscriptionUpgradeAndDowngradeReq req)
			throws Exception;

}
