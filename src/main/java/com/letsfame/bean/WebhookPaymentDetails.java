package com.letsfame.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Webhook")
//@JsonIgnoreProperties(value = { "subscriptionsId", "status" }, allowGetters = true)
public class WebhookPaymentDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169798492733221331L;

	@Id
	private String id;

	private WebhookContext context;
	private WebhookEvent event;
	private String subscriptionId;
	private Integer expireBy;
}
