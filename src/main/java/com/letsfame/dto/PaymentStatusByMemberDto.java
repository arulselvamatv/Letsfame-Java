package com.letsfame.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.letsfame.customJsonDeserializer.UnixTimestampDeserializer;
import com.letsfame.customJsonDeserializer.UnixTimestampSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentStatusByMemberDto {

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
	private String subscribedAt;

	@JsonProperty("expire_by")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Integer expiredAt;

}
