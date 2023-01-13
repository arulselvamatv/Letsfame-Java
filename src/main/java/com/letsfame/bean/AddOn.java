package com.letsfame.bean;

import java.util.ArrayList;

public class AddOn {

	public ArrayList<Item> items;

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "AddOn [items=" + items + "]";
	}

}

////public class AddOn {
////	private Items items;
////
////	public Items getItems() {
////		return items;
////	}
////
////	public void setItems(Items items) {
////		this.items = items;
////	}
////
////	@Override
////	public String toString() {
////		return "AddOn [items=" + items + "]";
////	}
//
////	public JSONObject toJsonObject() {
////		return item.toJsonObject();
////	}
