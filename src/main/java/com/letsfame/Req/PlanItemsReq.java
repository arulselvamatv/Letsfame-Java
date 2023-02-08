package com.letsfame.Req;

import com.letsfame.bean.Item;



public class PlanItemsReq {

	private Item items;

	@Override
	public String toString() {
		return "PlanItems [items=" + items + "]";
	}

	public Item getItem() {
		return items;
	}

	public void setItem(Item items) {
		this.items = items;
	}
}
