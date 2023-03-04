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
@Document(collection = "Item")
public class LetsFamePlanItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1508992171465029322L;

	@Id
	private String id;

	private String name;
	private int amount;
	private String currency;
	private String description;
	private String planId;
	private Integer createdAt;
	private Integer updatedAt;
}