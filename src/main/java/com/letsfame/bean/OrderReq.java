package com.letsfame.bean;

import java.util.List;

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
//@Entity
//@DynamicInsert
//@DynamicUpdate
@Builder
@Document(collection = "Order")
public class OrderReq {

	@Id
	private String id;

	private String orderId;
	private Double amount;
	private String currency;
	private String receipt;
	// private String Status;

	@Override
	public String toString() {
		return "OrderRequest [id=" + id + ", amount=" + amount + ", currency=" + currency + ", receipt=" + receipt
				+ "]";
	}

	public JSONObject toJsonObject() {
		JSONObject orderRequest = new JSONObject();

		orderRequest.put("amount", this.amount); // amount in the smallest currency unit

		orderRequest.put("currency", this.currency != null ? this.currency : "INR");// Default INR amount

		orderRequest.put("receipt", this.receipt);

//		orderRequest.put("Status", this.Status);

		return orderRequest;
	}

}
