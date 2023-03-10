package com.letsfame.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderCreateRequest {

	@JsonProperty("amount")
	private String amount;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("receipt")
	private String receipt;

}
