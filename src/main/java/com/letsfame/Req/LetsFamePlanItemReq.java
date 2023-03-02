package com.letsfame.Req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LetsFamePlanItemReq {

	@JsonProperty("name")
	private String name;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("description")
	private String description;

}
