package com.letsfame.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.letsfame.bean.Addons;
import com.letsfame.bean.Notes;
import com.letsfame.bean.subCustomerNotifyInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Document(collection = "subscription")
public class SubscriptionsDto implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Id
//	public Id id;

	private String memberId;

	private String plan_id;

	private int total_count;

	private int quantity;

	private Integer start_at;

	private Integer expire_by;

	private Boolean customer_notify;

	private ArrayList<Addons> addons;

	private String offer_id;

	private Notes notes;

	private subCustomerNotifyInfo notify_info;

}
