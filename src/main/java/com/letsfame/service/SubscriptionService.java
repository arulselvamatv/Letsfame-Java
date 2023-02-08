package com.letsfame.service;

import java.util.List;

import com.letsfame.bean.Subscriptions;
import com.razorpay.RazorpayException;
import com.razorpay.Subscription;

public interface SubscriptionService {

	Subscription createSubscription(Subscriptions req) throws RazorpayException;
	
	List<Subscriptions> getsubscriptions();
}
