package com.letsfame.request;

import lombok.Data;

@Data
public class PlanItemCreateRequest {

	private PlanItemNameRequest name;

	private Double amount;

	private String currency;

	private String description;

}
