package com.letsfame.request;

import com.letsfame.bean.types.PlanItemName;

import lombok.Data;

@Data
public class PlanItemCreateRequest {

	private PlanItemName name;

	private Double amount;

	private String currency;

	private String description;

}
