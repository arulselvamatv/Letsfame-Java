package com.letsfame.Req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LetsFameOrderReq {

	@JsonProperty("amount")
	private String amount;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("receipt")
	private String receipt;

}
