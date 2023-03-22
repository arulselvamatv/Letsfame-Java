package com.letsfame.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SubscriptionUpgradeAndDowngradeRequest {

	@JsonProperty("plan_id")
	private String planId;

	@JsonProperty("quantity")
	private String quantity;

	@JsonProperty("remaining_count")
	private String remainingCount;

	@JsonProperty("schedule_change_at")
	private String scheduleChangeAt;

	@JsonProperty("customer_notify")
	private String customerNotify;

	@JsonProperty("offer_id")
	private String offerId;

}
