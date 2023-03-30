package com.letsfame.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

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
public class PlanItem implements Serializable {

	private static final long serialVersionUID = 1508992171465029322L;

	@Id
	private String id;

	private String name;
	private int amount;
	private String currency;
	private String description;
	private String planId;

	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date createdAt;
	@JsonDeserialize(using = UnixTimestampDeserializer.class)
	@JsonSerialize(using = UnixTimestampSerializer.class)
	private Date updatedAt;
}