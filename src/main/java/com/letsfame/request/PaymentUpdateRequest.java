package com.letsfame.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PaymentUpdateRequest {

	@JsonProperty("payment_id")
	private String paymentId;
}
