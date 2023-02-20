package com.letsfame.dto;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.letsfame.webhook.Context;
import com.letsfame.webhook.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "Webhook")
//@JsonIgnoreProperties(value = { "subscriptionsId", "status" }, allowGetters = true)
public class PaymentDetailsWebhookDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Context context;
	private Event event;
	private String  subscriptionId;
	private Integer expireBy;
}
