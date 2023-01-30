package com.letsfame.bean;

import java.util.List;

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
@Document(collection = "Plan")
public class PlanReq {
	@Id
	private String id;
	private Double amount;
	private String period;
	private String interval;
	private String name;
	private String currency;
	private List<String> features;

	@Override
	public String toString() {
		return "PlanReq [id=" + id + ", amount=" + amount + ", period=" + period + ", interval=" + interval + ", name="
				+ name + ", currency=" + currency + ", features=" + features + "]";
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
