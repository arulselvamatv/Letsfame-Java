package com.letsfame.bean;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.letsfame.customJsonDeserializer.UnixTimestampDeserializer;
import com.letsfame.customJsonDeserializer.UnixTimestampSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WebhookPaymentStatusByMember {

	private String razorCustomerId;
	private String subscriptionId;
	private String paymentId;
	private String paymentFrequency;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date subscribedAt;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date expiredAt;

//	@JsonProperty("razor_customer_id")
//	private String razorCustomerId;
//
//	@JsonProperty("subscription_id")
//	private String subscriptionId;
//
//	@JsonProperty("payment_id")
//	private String paymentId;
//
//	@JsonProperty("payment_frequency")
//	private String paymentFrequency;
//
//	@JsonDeserialize(using = UnixTimestampDeserializer.class)
//	@JsonSerialize(using = UnixTimestampSerializer.class)
//	@JsonProperty("created_at")
//	private Date subscribedAt;
//
//	@JsonProperty("expire_by")
//	@JsonDeserialize(using = UnixTimestampDeserializer.class)
//	@JsonSerialize(using = UnixTimestampSerializer.class)
//	private Date expiredAt;

}
