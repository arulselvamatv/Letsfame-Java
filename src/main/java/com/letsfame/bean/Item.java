package com.letsfame.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "item")
public class Item {

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [description=" + description + "]";
	}

//	public JSONObject toJsonObject() {
//		JSONObject item = new JSONObject();
//
//		item.put("amount", this.amount); // amount in the smallest currency unit
//
//		item.put("name", this.name);
//
//		item.put("currency", this.currency != null ? this.currency : "INR");// Default INR amount
//
//		item.put("description", this.description);
//
//		return item;
//	}

}
