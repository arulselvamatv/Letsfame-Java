package com.letsfame.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SubscriptionCustomerInfoRequest {

	@JsonProperty("notify_phone")
	private String notifyPhone;
	@JsonProperty("notify_email")
	private String notifyEmail;
}
