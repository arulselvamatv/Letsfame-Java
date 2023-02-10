package com.letsfame.webhook;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Event {
	private String entity;
	private String account_id;
	private String event;
	private ArrayList<String> contains;
	private Payload payload;
	private int created_at;

}
