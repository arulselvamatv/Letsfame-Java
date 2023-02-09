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
	public String entity;
	public String account_id;
	public String event;
	public ArrayList<String> contains;
	public Payload payload;
	public int created_at;

}
