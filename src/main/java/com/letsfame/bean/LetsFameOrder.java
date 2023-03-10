package com.letsfame.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Order")
public class LetsFameOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6003169233927933680L;

	@Id
	private String id;

	private String orderId;
	private Double amount;
	private String currency;
	private String receipt;
}
