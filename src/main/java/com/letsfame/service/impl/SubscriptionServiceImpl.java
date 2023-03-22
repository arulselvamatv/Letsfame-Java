package com.letsfame.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsfame.bean.Subscription;
import com.letsfame.bean.SubscriptionNote;
import com.letsfame.dto.PaginationDto;
import com.letsfame.repository.SubscriptionRepository;
import com.letsfame.request.SubscriptionCreateRequest;
import com.letsfame.request.SubscriptionUpgradeAndDowngradeRequest;
import com.letsfame.service.SubscriptionService;
import com.letsfame.util.DateUtils;
import com.razorpay.RazorpayClient;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRequestRepository;

	@Autowired
	private RazorpayClient razorpayClient;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Subscription createSubscription(SubscriptionCreateRequest req) throws Exception {

		JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(req));

		Subscription responseData = new Subscription();

		// we dn't need to send memberId to razorpay so we remove this memberid in json
		// object
		jsonObject.remove("member_id");

		com.razorpay.Subscription subscription = razorpayClient.subscriptions.create(jsonObject);

		if (subscription != null) {

			responseData = razorPaySubscriptionToLetsFameSubscription(subscription);
			responseData.setMemberId(req.getMemberId());
			subscriptionRequestRepository.save(responseData);
		}

		return responseData;

	}

	private Subscription razorPaySubscriptionToLetsFameSubscription(com.razorpay.Subscription subscription) {
		Subscription savedData1 = new Subscription();
		SubscriptionNote saveNotes = new SubscriptionNote();
		JSONObject subscriptionJsonObject = subscription.toJson();
		savedData1.setSubscriptionsId(subscriptionJsonObject.getString("id"));
//		savedData1.setMemberId(req.getMemberId());
		savedData1.setPlanId(subscriptionJsonObject.getString("plan_id"));
		savedData1.setTotal_count(subscriptionJsonObject.getInt("total_count"));
		savedData1.setQuantity(subscriptionJsonObject.getInt("total_count"));

		if (!subscriptionJsonObject.isNull("start_at")) {
			savedData1.setStart_at(DateUtils.getRazorPayTimeStamp(subscriptionJsonObject.getInt("start_at")));
		}
		if (!subscriptionJsonObject.isNull("expire_by")) {
			savedData1.setExpire_by(DateUtils.getRazorPayTimeStamp(subscriptionJsonObject.getInt("expire_by")));
		}

		savedData1.setCustomer_notify(subscriptionJsonObject.getBoolean("customer_notify"));
		savedData1.setStatus(subscriptionJsonObject.getString("status"));
		savedData1.setShort_url(subscriptionJsonObject.getString("short_url"));

		savedData1.setRemaining_count(subscriptionJsonObject.getInt("remaining_count"));
		if (!subscriptionJsonObject.isNull("end_at")) {
			savedData1.setEnd_at(DateUtils.getRazorPayTimeStamp(subscriptionJsonObject.getInt("end_at")));
		}
		savedData1.setPaid_count(subscriptionJsonObject.getInt("paid_count"));
		if (!subscriptionJsonObject.isNull("created_at")) {
			savedData1.setCreated_at(DateUtils.getRazorPayTimeStamp(subscriptionJsonObject.getInt("created_at")));
		}
		if (!subscriptionJsonObject.isNull("charge_at")) {
			savedData1.setCharge_at(DateUtils.getRazorPayTimeStamp(subscriptionJsonObject.getInt("charge_at")));
		}

		JSONObject subscriptionNotesJsonObject = subscription.toJson().getJSONObject("notes");

		for (int i = 0; i < subscriptionNotesJsonObject.length(); i++) {
			saveNotes.setNotesKey1(subscriptionNotesJsonObject.getString("notes_key_1"));
			saveNotes.setNotesKey2(subscriptionNotesJsonObject.getString("notes_key_2"));

		}
		savedData1.setNotes(saveNotes);

		return savedData1;

	}

	@Override
	public PaginationDto findAllsubscriptions(Integer pageNo, Integer pageSize, String sortBy) throws Exception {

		Pageable paging = PageRequest.of(pageNo, pageSize);

		Page<Subscription> pagedResult = subscriptionRequestRepository.findAll(paging);
		PaginationDto res = new PaginationDto();
		if (pagedResult.hasContent()) {
			res.setData(pagedResult.getContent());
			res.setPageNumber(pagedResult.getNumber());
			res.setPageSize(pagedResult.getTotalPages());
			res.setSorted(pagedResult.getSort().isSorted());
			res.setTotalElements(pagedResult.getTotalElements());
		}
		return res;

	}

	@Override
	public Subscription findOne(String id) throws Exception {
		return subscriptionRequestRepository.findById(id).get();
	}

	@Override
	public Subscription findBySubscriptionsId(String subscriptionsId) throws Exception {
		return subscriptionRequestRepository.findBySubscriptionsId(subscriptionsId);
	}

	@Override
	public Subscription cancelSubscription(String subscriptionsId) throws Exception {

		JSONObject jsonObject = new JSONObject();

		Subscription cancelledData = new Subscription();

		jsonObject.put("cancel_at_cycle_end", 0);

		com.razorpay.Subscription subscription = razorpayClient.subscriptions.cancel(subscriptionsId, jsonObject);

		cancelledData = razorPaySubscriptionToLetsFameSubscription(subscription);

		Subscription savedData = findBySubscriptionsId(subscriptionsId);

		System.out.println("cancelledData ::" + savedData);

		cancelledData.setId(savedData.getId());

		System.out.println("cancelledData ::" + cancelledData);

		subscriptionRequestRepository.save(cancelledData);

		return cancelledData;

	}

	@Override
	public Subscription pauseSubscription(String subscriptionsId) throws Exception {

		JSONObject jsonObject = new JSONObject();

		Subscription pausedData = new Subscription();

		jsonObject.put("pause_at", "now");

		com.razorpay.Subscription subscription = razorpayClient.subscriptions.pause(subscriptionsId, jsonObject);

		pausedData = razorPaySubscriptionToLetsFameSubscription(subscription);

		Subscription savedData = findBySubscriptionsId(subscriptionsId);

		System.out.println("cancelledData ::" + savedData);

		pausedData.setId(savedData.getId());

		System.out.println("cancelledData ::" + pausedData);

		subscriptionRequestRepository.save(pausedData);

		return pausedData;

	}

	@Override
	public Subscription resumeSubscription(String subscriptionsId) throws Exception {

		JSONObject jsonObject = new JSONObject();

		Subscription resumedData = new Subscription();

		jsonObject.put("resume_at", "now");

		com.razorpay.Subscription subscription = razorpayClient.subscriptions.resume(subscriptionsId, jsonObject);

		resumedData = razorPaySubscriptionToLetsFameSubscription(subscription);

		Subscription savedData = findBySubscriptionsId(subscriptionsId);

		System.out.println("cancelledData ::" + savedData);

		resumedData.setId(savedData.getId());

		System.out.println("resumedData ::" + resumedData);

		subscriptionRequestRepository.save(resumedData);

		return resumedData;

	}

	@Override
	public Subscription subscriptionUpgradeandDowngrade(String subscriptionsId,
			SubscriptionUpgradeAndDowngradeRequest req) throws Exception {

		SubscriptionUpgradeAndDowngradeRequest upgradeOrDowngradeData = new SubscriptionUpgradeAndDowngradeRequest();

		Subscription subscriptionUpgradeorDowngrade = new Subscription();

		JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(upgradeOrDowngradeData));

		com.razorpay.Subscription subscription = razorpayClient.subscriptions.update(subscriptionsId, jsonObject);

		subscriptionUpgradeorDowngrade = razorPaySubscriptionToLetsFameSubscription(subscription);

		Subscription savedData = findBySubscriptionsId(subscriptionsId);

		System.out.println("Subscription DB Data ::" + savedData);

		subscriptionUpgradeorDowngrade.setId(savedData.getId());

		System.out.println("subscriptionUpgradeorDowngrade ::" + subscriptionUpgradeorDowngrade);

		subscriptionRequestRepository.save(subscriptionUpgradeorDowngrade);

		return subscriptionUpgradeorDowngrade;

	}

}
