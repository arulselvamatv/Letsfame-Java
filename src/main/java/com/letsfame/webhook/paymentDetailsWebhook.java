package com.letsfame.webhook;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;


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
public class paymentDetailsWebhook implements Serializable {

	private static final long serialVersionUID = 1L;

	public Context context;
	public Event event;
}
