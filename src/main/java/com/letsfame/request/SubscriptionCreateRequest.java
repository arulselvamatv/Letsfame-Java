package com.letsfame.request;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.letsfame.bean.SubscriptionAddon;
import com.letsfame.bean.SubCustomerNotifyInfo;
import com.letsfame.custom.json.deserializer.UnixTimestampDeserializer;
import com.letsfame.custom.json.deserializer.UnixTimestampSerializer;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "subscriptionsId", "status", "short_url", "remaining_count", "end_at", "created_at",
		"charge_at", "paid_count" }, allowGetters = true)
public class SubscriptionCreateRequest {

	@JsonProperty("member_id")
	private String memberId;

	@JsonProperty("plan_id")
	private String planId;

	@JsonProperty("total_count")
	private Integer totalCount;

	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("start_at")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date startAt;

	@JsonProperty("expire_by")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date expireBy;

	@JsonProperty("customer_notify")
	private Boolean customerNotify;

	private SubcriptionNoteCreateRequest notes;

	private ArrayList<SubscriptionAddon> addons;

	@JsonProperty("offer_id")
	private String offerId;

	private SubCustomerNotifyInfo notify_info;

	@JsonProperty("status")
	private String status;

	@JsonProperty("short_url")
	private String shortUrl;

	@JsonProperty("remaining_count")
	private Integer remainingCount;

	@JsonProperty("end_at")
	private Date endAt;

	@JsonProperty("created_at")
	private Date createAt;

	@JsonProperty("charge_at")
	private Date chargeAt;

	@JsonProperty("paid_count")
	private Integer paidCount;

}
