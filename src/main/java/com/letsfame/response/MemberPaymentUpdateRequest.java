package com.letsfame.response;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.letsfame.custom.json.deserializer.UnixTimestampDeserializer;
import com.letsfame.custom.json.deserializer.UnixTimestampSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberPaymentUpdateRequest {

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

}
