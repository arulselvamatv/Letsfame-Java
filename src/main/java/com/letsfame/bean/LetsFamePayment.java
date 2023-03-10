package com.letsfame.bean;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.letsfame.customJsonDeserializer.UnixTimestampDeserializer;
import com.letsfame.customJsonDeserializer.UnixTimestampSerializer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Payment")
public class LetsFamePayment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -791767553029763519L;

	@Id
	private String id;

	private String paymentId;
	private String entity;
	private String status;
	private String description;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date createdAt;

	private Double amount_refunded;
	private String bank;
	private String contact;
	private String currency;
	private String email;
	private Double amount;
	private String method;
	private String orderId;
	private String invoiceId;
	private boolean international;
	private boolean captured;
	private Object cardId;
	private String wallet;
	private String vpa;
	private String customerId;
	private String tokenId;

}
