package com.letsfame.bean;

//import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
//import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

public class Items {

	public String description1;
	public String description2;
	public String description3;
	public String description4;
	public String description5;
	public String description6;

	public String getDescription1() {
		return description1;
	}

	public void setDescription1(String description1) {
		this.description1 = description1;
	}

	public String getDescription2() {
		return description2;
	}

	public void setDescription2(String description2) {
		this.description2 = description2;
	}

	public String getDescription3() {
		return description3;
	}

	public void setDescription3(String description3) {
		this.description3 = description3;
	}

	public String getDescription4() {
		return description4;
	}

	public void setDescription4(String description4) {
		this.description4 = description4;
	}

	public String getDescription5() {
		return description5;
	}

	public void setDescription5(String description5) {
		this.description5 = description5;
	}

	public String getDescription6() {
		return description6;
	}

	public void setDescription6(String description6) {
		this.description6 = description6;
	}

	@Override
	public String toString() {
		return "Items [description1=" + description1 + ", description2=" + description2 + ", description3="
				+ description3 + ", description4=" + description4 + ", description5=" + description5 + ", description6="
				+ description6 + "]";
	}

//public JSONObject toJsonObject() {
//JSONObject item = new JSONObject();
//
//item.put("amount", this.amount); // amount in the smallest currency unit
//
//item.put("name", this.name);
//
//item.put("currency", this.currency != null ? this.currency : "INR");// Default INR amount
//
//item.put("description", this.description);
//
//return item;
//}

}
