package com.letsfame.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.letsfame.bean.PlanNote;
import com.letsfame.bean.types.PlanPeriodName;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "created_at" }, allowGetters = true)
public class PlanCreateRequest {

	@JsonProperty("plan_id")
	private String id;

	@JsonProperty("interval")
	private int interval;

	private PlanPeriodName period;

	private PlanNote notes;

	private PlanItemCreateRequest item;

}