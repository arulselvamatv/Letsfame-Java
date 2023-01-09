package com.letsfame.bean;

import org.json.JSONObject;

public class AddOn {
	private Item item;

	public JSONObject toJsonObject() {
		return item.toJsonObject();
	}

	@Override
	public String toString() {
		return "AddOn [item=" + item + "]";
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
