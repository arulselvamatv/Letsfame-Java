package com.letsfame.bean;

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
public class WebhookEvent {
	private String entity;
	private String accountId;
	private String event;
	private ArrayList<String> contains;
	private WebhookPayload payload;
	private int createdAt;

}
