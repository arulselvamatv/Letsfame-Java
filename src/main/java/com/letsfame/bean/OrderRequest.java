package com.letsfame.bean;

import org.json.JSONObject;

public class OrderRequest {

	private Double amount;
	private String currency;
	private String receipt;

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
		return "OrderRequest [amount=" + amount + ", currency=" + currency + ", receipt=" + receipt + "]";
	}

	public JSONObject toJsonObject() {
		JSONObject orderRequest = new JSONObject();

		orderRequest.put("amount", this.amount); // amount in the smallest currency unit

		orderRequest.put("currency", this.currency != null ? this.currency : "INR");// Default INR amount

		orderRequest.put("receipt", this.receipt);
		return orderRequest;
	}

}
