package com.letsfame.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class IosPaymentUpdateRequest {

	@JsonProperty("transaction_id")
	private String paymentId;

	@JsonProperty("user_id")
	private String memberId;

	@JsonProperty("service_plan_id or product_id")
	private String planId;

	@JsonProperty("month_count")
	private Integer monthCount;

	@JsonProperty("price")
	private Double amount;

	@JsonProperty("is_payment")
	private Boolean payment;

	@JsonProperty("plan_purchase_date")
	private Date planCreatedDate;

	@JsonProperty("plan_expiry_date")
	private Date expireBy;

	@JsonProperty("purchased_device")
	private String purchasedDevice;

	@JsonProperty("is_recurring")
	private Boolean recurring;

}
