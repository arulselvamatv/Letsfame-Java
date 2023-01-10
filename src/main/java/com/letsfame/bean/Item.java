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
@Document(collection = "item")
public class Item {

	private Double amount;
	private String name;
	private String currency;
	private String description;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [amount=" + amount + ", name=" + name + ", currency=" + currency + ", description=" + description
				+ "]";
	}

	public JSONObject toJsonObject() {
		JSONObject item = new JSONObject();

		item.put("amount", this.amount); // amount in the smallest currency unit

		item.put("name", this.name);

		item.put("currency", this.currency != null ? this.currency : "INR");// Default INR amount

		item.put("description", this.description);

		return item;
	}

}
