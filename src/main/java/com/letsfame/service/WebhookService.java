package com.letsfame.service;

import java.util.List;

import com.letsfame.bean.WebhookPaymentDetails;
import com.letsfame.response.Response;

public interface WebhookService {

	Response webhookpaymentNotification(WebhookPaymentDetails notification);

	List<WebhookPaymentDetails> getWebhookNotification();
}