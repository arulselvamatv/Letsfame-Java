package com.letsfame.bean;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;

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
public class RazPlanReq {

	@Id
	private String id;

	private String name;
	private String planDescription;
	private Double amount;
	private String period;
	private Date createdAt;
	private List<String> notes;

	public JSONObject toJsonObject() {
		JSONObject planRequest = new JSONObject();

		planRequest.put("id", this.id);

		planRequest.put("name", this.name);

		planRequest.put("amount", this.amount);

		planRequest.put("planDescription", this.planDescription); // amount in the smallest currency unit

		planRequest.put("period", this.period);// Default INR amount

		planRequest.put("createdAt", this.createdAt);

		planRequest.put("notes", this.notes);

//		if (item != null) {
//			planRequest.put("item", this.item.toJsonObject());
//		}
//
		return planRequest;
	}

}
