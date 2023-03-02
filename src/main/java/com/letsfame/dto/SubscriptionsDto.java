package com.letsfame.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.letsfame.bean.LetsFameSubscriptionAddon;
import com.letsfame.bean.LetsFamePlanNote;
import com.letsfame.bean.SubCustomerNotifyInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class SubscriptionsDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String memberId;

	private String plan_id;

	private int total_count;

	private int quantity;

	private Integer start_at;

	private Integer expire_by;

	private Boolean customer_notify;

	private ArrayList<LetsFameSubscriptionAddon> addons;

	private String offer_id;

	private LetsFamePlanNote notes;

	private SubCustomerNotifyInfo notify_info;

}
