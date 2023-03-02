package com.letsfame.dto;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.letsfame.bean.WebhookContext;
import com.letsfame.bean.WebhookEvent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Webhook")
//@JsonIgnoreProperties(value = { "subscriptionsId", "status" }, allowGetters = true)
public class WebhookPaymentDetailsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169798492733221331L;
	private WebhookContext context;
	private WebhookEvent event;
	private String  subscriptionId;
	private Integer expireBy;
}
