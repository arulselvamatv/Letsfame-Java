package com.letsfame.bean;

import org.json.JSONObject;
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
@Document(collection = "planrequest")
public class PlanRequest {
	private String id;
	private Double amount;
	private String period;
	private String interval;
	private Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
		return "PlanRequest [amount=" + amount + ", period=" + period + ", interval=" + interval + "]";
	}

	public JSONObject toJsonObject() {
		JSONObject planRequest = new JSONObject();

		planRequest.put("amount", this.amount); // amount in the smallest currency unit

		planRequest.put("period", this.period);// Default INR amount

		planRequest.put("interval", this.interval);
		
		planRequest.put("id", this.id);

		if (item != null) {
			planRequest.put("item", this.item.toJsonObject());
		}

		return planRequest;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
