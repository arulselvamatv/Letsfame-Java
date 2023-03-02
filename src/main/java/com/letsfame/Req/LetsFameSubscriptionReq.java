package com.letsfame.Req;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.letsfame.bean.LetsFameSubscriptionAddon;
import com.letsfame.bean.SubCustomerNotifyInfo;
import com.letsfame.customJsonDeserializer.UnixTimestampDeserializer;
import com.letsfame.customJsonDeserializer.UnixTimestampSerializer;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "subscriptionsId", "status", "short_url", "remaining_count", "end_at", "created_at",
		"charge_at", "paid_count" }, allowGetters = true)
public class LetsFameSubscriptionReq {

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

	private LetsFameSubcriptionNoteReq notes;

	private ArrayList<LetsFameSubscriptionAddon> addons;

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
