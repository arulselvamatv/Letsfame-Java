package com.letsfame.request;

import lombok.Data;

@Data
public class PlanItemCreateRequest {

	private String name;

	private int amount;

	private String currency;

	private String description;

}
