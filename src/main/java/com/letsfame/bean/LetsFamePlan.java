package com.letsfame.bean;

import java.io.Serializable;
import java.util.Date;

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
@Document(collection = "Plan")
public class LetsFamePlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6659778483040242320L;
	
	@Id
	private String id;
	
	private String planId;

	private String period;

	private int interval;

	private LetsFamePlanNote notes;

	private LetsFamePlanItem item;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date createdAt;

}
