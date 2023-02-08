package com.letsfame.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Document(collection = "subscription")
@JsonIgnoreProperties(value = { "subscriptionsId" , "status" , "short_url" , "remaining_count", "end_at","created_at","charge_at","paid_count"}, allowGetters = true)
public class Subscriptions implements Serializable {

	private static final long serialVersionUID = 1L;

	private String subscriptionsId;

	private String memberId;

	private String plan_id;

	private int total_count;

	private int quantity;

	private Date start_at;

	private Date expire_by;

	private Boolean customer_notify;

	private ArrayList<Addons> addons;

	private String offer_id;

	private Notes notes;

	private subCustomerNotifyInfo notify_info;

	private String status;

	private String short_url;

	private int remaining_count;
	
	private Date end_at;
	
	private Date created_at;
	
	private Date charge_at;
	
	private int paid_count;

}
