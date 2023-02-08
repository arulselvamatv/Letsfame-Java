package com.letsfame.bean;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "Order")
public class Orders {

	@Id
	private String id;

	private String orderId;
	private Double amount;
	private String currency;
	private String receipt;

	public JSONObject toJsonObject() {
		JSONObject orderRequest = new JSONObject();

		orderRequest.put("amount", this.amount); // amount in the smallest currency unit

		orderRequest.put("currency", this.currency != null ? this.currency : "INR");// Default INR amount

		orderRequest.put("receipt", this.receipt);

		return orderRequest;
	}

}
