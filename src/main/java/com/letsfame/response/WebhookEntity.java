package com.letsfame.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WebhookEntity {
	
	@JsonProperty("id")
	private String paymentId;
	private String entity;
	private int amount;
	private String currency;
	private String status;
	private String order_id;
	private String invoice_id;
	private boolean international;
	private String method;
	private int amount_refunded;
	private Object refund_status;
	private boolean captured;
	private String description;
	private Object cardId;
	private Object bank;
	private Object wallet;
	private String vpa;
	private String email;
	private String contact;
	private String customer_id;
	private String token_id;
	private WebhookNote notes;
	private Object fee;
	private Object tax;
	private Object errorCode;
	private Object errorDescription;
	private Object errorSource;
	private Object errorStep;
	private Object errorReason;
	private WebhookAcquirerData acquirerData;
	private int created_at;
}
