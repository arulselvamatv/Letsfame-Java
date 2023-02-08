package com.letsfame.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
@JsonIgnoreProperties(value = { "planId" }, allowGetters = true)
@Document(collection = "Plan")
public class Plans implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String planId;
	
	@NotNull
	private String period;
	
	@NotNull
	private int interval;
	
	
	private Notes notes;
	
	@NotNull
	private Item item;

}
