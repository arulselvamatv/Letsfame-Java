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
@JsonIgnoreProperties(value = { "id", "planId" }, allowGetters = true)
@Document(collection = "Item")
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private String name;
	private int amount;
	private String currency;
	private String description;
	private String planId;
}