package com.letsfame.bean;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.qos.logback.core.net.SyslogOutputStream;

@Document(collection = "PlanReq")
public class PlanReq {

	private String id;
	private Double amount;
	private String period;
	private String interval;
	private String name;
	private String currency;
	@JsonProperty("Items") 
    public Items items;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	@Override
	public String toString() {
		return "PlanReq [id=" + id + ", amount=" + amount + ", period=" + period + ", interval=" + interval + ", name="
				+ name + ", currency=" + currency + ", items=" + items + "]";
	}

//	public JSONObject toJsonObject() {
//	JSONObject item = new JSONObject();
//
//	item.put("amount", this.amount); // amount in the smallest currency unit
//
//	item.put("name", this.name);
//
//	item.put("currency", this.currency != null ? this.currency : "INR");// Default INR amount
//
//	item.put("description", this.description);
//
//	return item;
//}

}
