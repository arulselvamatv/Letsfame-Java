package com.letsfame.webhook;

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
public class Entity {

	private String id;
	private String entity;
	private int amount;
	private String currency;
	private String status;
	private String order_id;
	private String invoice_id;
	private boolean international;
	private String method;
	private int amount_refunded;
	private Object refund_status;
	private boolean captured;
	private String description;
	private Object card_id;
	private Object bank;
	private Object wallet;
	private String vpa;
	private String email;
	private String contact;
	private String customer_id;
	private String token_id;
	private Notes notes;
	private Object fee;
	private Object tax;
	private Object error_code;
	private Object error_description;
	private Object error_source;
	private Object error_step;
	private Object error_reason;
	private AcquirerData acquirer_data;
	private int created_at;
}
