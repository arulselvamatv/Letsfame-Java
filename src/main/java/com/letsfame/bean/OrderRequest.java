package com.letsfame.bean;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Order_Details")
public class OrderRequest {

	@Id
	private String id;
	private String orderId;
	private Double amount;
	private String currency;
	private String receipt;
	
//	private String Status;



//
//	public String getStatus() {
//		return Status;
//	}
//
//	public void setStatus(String status) {
//		Status = status;
//	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	

	

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
