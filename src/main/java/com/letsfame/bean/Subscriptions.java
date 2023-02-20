package com.letsfame.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.letsfame.customJsonDeserializer.UnixTimestampDeserializer;
import com.letsfame.customJsonDeserializer.UnixTimestampSerializer;

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
@JsonIgnoreProperties(value = { "subscriptionsId", "status", "short_url", "remaining_count", "end_at", "created_at",
		"charge_at", "paid_count" }, allowGetters = true)
public class Subscriptions implements Serializable {

	private static final long serialVersionUID = 1L;

	private String subscriptionsId;

	private String memberId;

	// private Subscription sub;

	private String plan_id;

	private Integer total_count;

	private Integer quantity;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date start_at;

//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date expire_by;

	private Boolean customer_notify;

	private ArrayList<Addons> addons;

	private String offer_id;

	private Notes notes;

	private subCustomerNotifyInfo notify_info;

	private String status;

	private String short_url;

	private Integer remaining_count;

	private Date end_at;

	private Date created_at;

	private Date charge_at;

	private Integer paid_count;

}
