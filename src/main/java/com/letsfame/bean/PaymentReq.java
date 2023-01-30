package com.letsfame.bean;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

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
//@Entity
//@DynamicInsert
//@DynamicUpdate
@Builder
@Document(collection = "Payment")
public class PaymentReq {

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
