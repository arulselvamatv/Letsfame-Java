package com.letsfame.bean;

import java.io.Serializable;
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
@Document(collection = "Payment")
public class Payment implements Serializable {

	private static final long serialVersionUID = -791767553029763519L;

	@Id
	private String id;

	private String paymentId;
	private Double amount;
	private String currency;
	private String method;
	private String orderId;
	private String invoiceId;
	private String customerId;
	private String email;
	private String contact;
	private String entity;
	private String status;
	private boolean captured;
	private String bank;
	private boolean international;
	private String description;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date createdAt;
	
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date expireBy;

	private Double amount_refunded;
	private String cardId;
	private String wallet;
	private String vpa;
	private String tokenId;

	private String memberId;
	private String planId;
	private Integer monthCount;
	private boolean payment;
	private String purchasedDevice;
	private boolean recurring;
}
