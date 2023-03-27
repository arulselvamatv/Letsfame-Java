package com.letsfame.response;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Webhook")
@JsonIgnoreProperties(value = { "id", "subscriptionsId", "status" }, allowGetters = true)
public class WebhookEventNotification implements Serializable {

	private static final long serialVersionUID = 5169798492733221331L;

	private WebhookContext context;
	private WebhookEvent event;
	private String subscriptionId;
	private Integer expireBy;
}
