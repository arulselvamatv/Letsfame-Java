package com.letsfame.webhook;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class PaymentStatusByMember {

	@JsonProperty("razor_customer_id")
	private String razorCustomerId;
	
	@JsonProperty("subscription_id")
	private String subscriptionId;
	
	@JsonProperty("payment_id")
	private String paymentId;
	
	@JsonProperty("payment_frequency")
	private String paymentFrequency;
	
	@JsonProperty("subscribed_at")
	private String subscribedAt;
	
	@JsonProperty("expired_at")
	private String expiredAt;
	

}


