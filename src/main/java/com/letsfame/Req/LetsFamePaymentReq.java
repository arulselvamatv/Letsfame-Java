package com.letsfame.Req;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LetsFamePaymentReq {

	@JsonProperty("payment_id")
	private String paymentId;
}
