package com.letsfame.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WebhookAcquirerData {
	private String rrn;
	private String upiTransactionId;
}
