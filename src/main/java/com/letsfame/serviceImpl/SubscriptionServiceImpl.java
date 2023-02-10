package com.letsfame.serviceImpl;

import java.util.Date;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.letsfame.bean.Notes;
import com.letsfame.bean.Subscriptions;
import com.letsfame.dto.SubscriptionsDto;
import com.letsfame.repository.SubscriptionRequestRepository;
import com.letsfame.service.SubscriptionService;
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
		
		SubscriptionsDto savedData = new SubscriptionsDto();
		Subscriptions savedData1 = new Subscriptions();
		Notes saveNotes = new Notes();

			int startAt = (int) (req.getStart_at().getTime() / 1000);
			int expireBy = (int) (req.getExpire_by().getTime() / 1000);
			savedData.setPlan_id(req.getPlan_id());
			savedData.setTotal_count(req.getTotal_count());
			savedData.setQuantity(req.getQuantity());
			savedData.setStart_at(startAt);
			savedData.setExpire_by(expireBy);
			savedData.setCustomer_notify(req.getCustomer_notify());
			savedData.setOffer_id(req.getOffer_id());
			savedData.setNotes(req.getNotes());
			savedData.setNotify_info(req.getNotify_info());
			Subscription subscription = razorpayClient.subscriptions.create(new JSONObject(new Gson().toJson(savedData)));
			
			if (subscription != null) {
				
				savedData1.setPlan_id(subscription.toJson().getString("plan_id"));
				savedData1.setTotal_count(subscription.toJson().getInt("total_count"));
				savedData1.setQuantity(subscription.toJson().getInt("total_count"));
				savedData1.setStart_at(new Date(subscription.toJson().getLong("start_at")));
				savedData1.setExpire_by(new Date(subscription.toJson().getLong("expire_by")));
				savedData1.setCustomer_notify(subscription.toJson().getBoolean("customer_notify"));
				savedData1.setStatus(subscription.toJson().getString("status"));
				savedData1.setShort_url(subscription.toJson().getString("short_url"));
				savedData1.setSubscriptionsId(subscription.toJson().getString("id"));
				savedData1.setRemaining_count(subscription.toJson().getInt("remaining_count"));
				savedData1.setEnd_at(new Date(subscription.toJson().getInt("end_at")));
				savedData1.setPaid_count(subscription.toJson().getInt("paid_count"));
				savedData1.setCreated_at(new Date(subscription.toJson().getInt("created_at")));
				savedData1.setCharge_at(new Date(subscription.toJson().getInt("charge_at")));
				
				savedData1.setMemberId(req.getMemberId());
				
				JSONObject newJSONnotes = subscription.toJson().getJSONObject("notes");
				for (int i = 0; i < newJSONnotes.length(); i++) {
					saveNotes.setNotes_key_1(newJSONnotes.getString("notes_key_1"));
					saveNotes.setNotes_key_2(newJSONnotes.getString("notes_key_2"));
					savedData1.setNotes(saveNotes);
				}
				
			}
			
			
			subscriptionRequestRepository.save(savedData1);
			return subscription;
			

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
