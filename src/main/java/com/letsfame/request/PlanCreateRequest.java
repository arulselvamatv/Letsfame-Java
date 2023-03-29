package com.letsfame.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "created_at" }, allowGetters = true)
public class PlanCreateRequest {

	@JsonProperty("plan_id")
	private String id;

	@JsonProperty("interval")
	private int interval;

	private PlanPeriodCreateRequest period;

	private PlanNoteCreateRequest notes;

	private PlanItemCreateRequest item;

}