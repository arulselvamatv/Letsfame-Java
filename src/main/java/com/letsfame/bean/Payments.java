package com.letsfame.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document(collection = "Payment")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class Payments implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String status;
	private String description;
	private String createdAt;
	private String amount_refunded;
	private String bank;
	private String contact;
	private String currency;
	private String email;
	private Double amount;
	private String method;
	private String order_id;

}
