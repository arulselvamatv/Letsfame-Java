package com.letsfame.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class WebhookPaymentStatusByMemberDto {

	@JsonProperty("razor_customer_id")
	private String razorCustomerId;

	@JsonProperty("subscription_id")
	private String subscriptionId;

	@JsonProperty("payment_id")
	private String paymentId;

	@JsonProperty("payment_frequency")
	private String paymentFrequency;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	@JsonProperty("created_at")
	private Date subscribedAt;

	@JsonProperty("expire_by")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date expiredAt;

}
