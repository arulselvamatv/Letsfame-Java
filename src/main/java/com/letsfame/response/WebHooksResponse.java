package com.letsfame.response;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "webhooks")
public class WebHooksResponse {

	private String id;

	private int created_at;

	private int updated_at;

	private String owner_id;

	private String owner_type;

	private String url;

	private String secret;

	private String alert_email;

	private boolean secret_exists;

	private String entity;

	private String active;

	private List <String> events;

}
