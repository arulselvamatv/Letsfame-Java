package com.letsfame.serviceImpl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsfame.bean.Notes;
import com.letsfame.bean.Subscriptions;
import com.letsfame.repository.SubscriptionRequestRepository;
import com.letsfame.service.SubscriptionService;
import com.letsfame.util.DateUtils;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Subscription;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRequestRepository subscriptionRequestRepository;

	@Autowired
	private RazorpayClient razorpayClient;

	@Override
	public Subscription createSubscription(Subscriptions req) throws RazorpayException {

//		SubscriptionsDto savedData = new SubscriptionsDto();
		Subscriptions savedData1 = new Subscriptions();
		Notes saveNotes = new Notes();
		try {
//			int startAt = (int) (req.getStart_at().getTime() / 1000);
//			int expireBy = (int) (req.getExpire_by().getTime() / 1000);
//			savedData.setPlan_id(req.getPlan_id());
//			savedData.setTotal_count(req.getTotal_count());
//			savedData.setQuantity(req.getQuantity());
//			savedData.setStart_at(startAt);
//			System.out.println("startAt date:::" + startAt);
//			savedData.setExpire_by(expireBy);
//			savedData.setCustomer_notify(req.getCustomer_notify());
//			savedData.setOffer_id(req.getOffer_id());
//			savedData.setNotes(req.getNotes());
//			savedData.setNotify_info(req.getNotify_info());
//			Subscription subscription = razorpayClient.subscriptions.create(new JSONObject(new Gson().toJson(savedData)));

			ObjectMapper objectMapper = new ObjectMapper();

			JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(req));
			System.out.println("gson value:::" + jsonObject);
			jsonObject.remove("memberId");

			Subscription subscription = razorpayClient.subscriptions.create(jsonObject);
			System.out.println("subscription value:::" + subscription);
			if (subscription != null) {
				JSONObject subscriptionJsonObject = subscription.toJson();
				// savedData1.setSub(subscription);
				savedData1.setSubscriptionsId(subscriptionJsonObject.getString("id"));
				savedData1.setMemberId(req.getMemberId());
				savedData1.setPlan_id(subscriptionJsonObject.getString("plan_id"));
				savedData1.setTotal_count(subscriptionJsonObject.getInt("total_count"));
				savedData1.setQuantity(subscriptionJsonObject.getInt("total_count"));

				if (!subscriptionJsonObject.isNull("start_at")) {
					savedData1.setStart_at(DateUtils.getRazorPayTimeStamp(subscriptionJsonObject.getInt("start_at")));
				}
				if (!subscriptionJsonObject.isNull("expire_by")) {
					savedData1.setExpire_by(DateUtils.getRazorPayTimeStamp(subscriptionJsonObject.getInt("expire_by")));
				}
//			savedData1.setExpire_by(new Date(subscriptionJsonObject.get("expire_by")));
				savedData1.setCustomer_notify(subscriptionJsonObject.getBoolean("customer_notify"));
				savedData1.setStatus(subscriptionJsonObject.getString("status"));
				savedData1.setShort_url(subscriptionJsonObject.getString("short_url"));

				savedData1.setRemaining_count(subscriptionJsonObject.getInt("remaining_count"));
				if (!subscriptionJsonObject.isNull("end_at")) {
					savedData1.setEnd_at(new Date(subscriptionJsonObject.getInt("end_at")));
				}
				savedData1.setPaid_count(subscriptionJsonObject.getInt("paid_count"));
				if (!subscriptionJsonObject.isNull("created_at")) {
					savedData1.setCreated_at(new Date(subscriptionJsonObject.getInt("created_at")));
				}
				if (!subscriptionJsonObject.isNull("charge_at")) {
					savedData1.setCharge_at(new Date(subscriptionJsonObject.getInt("charge_at")));
				}

				JSONObject newJSONnotes = subscriptionJsonObject.getJSONObject("notes");
				for (int i = 0; i < newJSONnotes.length(); i++) {
					saveNotes.setNotes_key_1(newJSONnotes.getString("notes_key_1"));
					saveNotes.setNotes_key_2(newJSONnotes.getString("notes_key_2"));
					savedData1.setNotes(saveNotes);
				}

			}

			subscriptionRequestRepository.save(savedData1);
			return subscription;
		} catch (Exception e) {
			System.out.println("Exception" + ExceptionUtils.getStackTrace(e));

		}
		return null;

	}

	@Override
	public List<Subscriptions> getsubscriptions() {

		return subscriptionRequestRepository.findAll();

	}

	@Override
	public Subscriptions findOne(String id) {
		return subscriptionRequestRepository.findById(id).get();
	}

	@Override
	public Subscriptions findBySubscriptionsId(String subscriptionsId) {
		return subscriptionRequestRepository.findBySubscriptionsId(subscriptionsId);
	}

}
