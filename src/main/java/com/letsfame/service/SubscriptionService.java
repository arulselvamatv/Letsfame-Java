package com.letsfame.service;

import com.letsfame.bean.Subscription;
import com.letsfame.dto.PaginationDto;
import com.letsfame.request.SubscriptionCreateRequest;
import com.letsfame.request.SubscriptionUpdateRequest;

public interface SubscriptionService {

	Subscription createSubscription(SubscriptionCreateRequest req) throws Exception;

	PaginationDto findAllsubscriptions(Integer pageNo, Integer pageSize, String sortBy) throws Exception;

	Subscription findOne(String id) throws Exception;

	Subscription findBySubscriptionsId(String subscriptionsId) throws Exception;

	Subscription findSubscriptionByMemberId(String memberId) throws Exception;

	Subscription cancelSubscription(String subscriptionsId) throws Exception;

	Subscription pauseSubscription(String subscriptionsId) throws Exception;

	Subscription resumeSubscription(String subscriptionsId) throws Exception;

	Subscription subscriptionUpgradeandDowngrade(String subscriptionsId, SubscriptionUpdateRequest req)
			throws Exception;

}
