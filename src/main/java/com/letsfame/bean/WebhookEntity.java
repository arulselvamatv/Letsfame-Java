package com.letsfame.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WebhookEntity {

	private String id;
	private String entity;
	private int amount;
	private String currency;
	private String status;
	private String orderId;
	private String invoiceId;
	private boolean international;
	private String method;
	private int amountRefunded;
	private Object refundStatus;
	private boolean captured;
	private String description;
	private Object cardId;
	private Object bank;
	private Object wallet;
	private String vpa;
	private String email;
	private String contact;
	private String customerId;
	private String tokenId;
	private WebhookNote notes;
	private Object fee;
	private Object tax;
	private Object errorCode;
	private Object errorDescription;
	private Object errorSource;
	private Object errorStep;
	private Object errorReason;
	private WebhookAcquirerData acquirerData;
	private int createdAt;
}
