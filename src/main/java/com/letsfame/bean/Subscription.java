package com.letsfame.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.letsfame.custom.json.deserializer.UnixTimestampDeserializer;
import com.letsfame.custom.json.deserializer.UnixTimestampSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "subscription")

public class Subscription implements Serializable {

	private static final long serialVersionUID = -5057897531225670585L;

	@Id
	private String id;

	private String subscriptionsId;

	private String memberId;

	private String planId;

	private Integer total_count;

	private Integer quantity;

	private Boolean customer_notify;

	private SubCustomerNotifyInfo notify_info;

	private String status;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date start_at;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date expire_by;

	private Date end_at;

	private Date created_at;

	private Date charge_at;

	private ArrayList<SubscriptionAddon> addons;

	private String offer_id;

	private SubscriptionNote notes;

	private String short_url;

	private Integer remaining_count;

	private Integer paid_count;

}
