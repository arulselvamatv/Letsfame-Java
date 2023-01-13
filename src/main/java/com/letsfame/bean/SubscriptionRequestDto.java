package com.letsfame.bean;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequestDto {

	@JsonProperty("total_count")
	private Double totalCount;
	@JsonProperty("plan_id")
	private String planId;
	@JsonProperty("quantity")
	private String quantity;
	@JsonProperty("customer_notify")
	private String customerNotify;
	@JsonProperty("start_at")
	private int startAt;
	@JsonProperty("expire_by")
	private int expireBy;
	@JsonProperty("addons")
	private List<AddOn> addons;

	public JSONObject toJsonObject() {
		JSONObject subscriptionRequest = new JSONObject();

		subscriptionRequest.put("total_count", this.totalCount);

		subscriptionRequest.put("quantity", this.quantity);

		subscriptionRequest.put("customer_notify", Integer.parseInt(this.customerNotify));

		subscriptionRequest.put("start_at", this.startAt);

		subscriptionRequest.put("expire_by", this.expireBy);

		subscriptionRequest.put("plan_id", this.planId);

		if (addons != null) {

			JSONArray jsonarray = new JSONArray();

			for (AddOn addOn : addons) {
//			jsonarray.add(item.toJsonObject());                                                                
				JSONObject itemObject = new JSONObject();
//			itemObject.put("item", addOn.toJsonObject());                                                      

				jsonarray.put(itemObject);

			}
			subscriptionRequest.put("addons", jsonarray);
		}

		return subscriptionRequest;
	}
}
