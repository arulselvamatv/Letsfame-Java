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

	public String id;
	public String entity;
	public int amount;
	public String currency;
	public String status;
	public String order_id;
	public Object invoice_id;
	public boolean international;
	public String method;
	public int amount_refunded;
	public Object refund_status;
	public boolean captured;
	public String description;
	public Object card_id;
	public Object bank;
	public Object wallet;
	public String vpa;
	public String email;
	public String contact;
	public String customer_id;
	public String token_id;
	public Notes notes;
	public Object fee;
	public Object tax;
	public Object error_code;
	public Object error_description;
	public Object error_source;
	public Object error_step;
	public Object error_reason;
	public AcquirerData acquirer_data;
	public int created_at;
}
