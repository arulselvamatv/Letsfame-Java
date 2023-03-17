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
@Document(collection = "Plan")
public class Plan implements Serializable {

	
	private static final long serialVersionUID = 6659778483040242320L;
	
	@Id
	private String id;
	
	private String planId;

	private String period;

	private int interval;

	private PlanNote notes;

	private PlanItem item;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date createdAt;

}
