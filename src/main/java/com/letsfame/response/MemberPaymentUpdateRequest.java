package com.letsfame.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberPaymentUpdateRequest {

	private String razor_customer_id;
	private String member_subscription_id;
	private String payment_id;
	private String payment_frequency;
	private String subscription_package;

	private String subscribed_at;

	private String expired_at;

}
