package com.letsfame.service;

import com.letsfame.response.Response;
import com.letsfame.response.WebhookEventNotification;

public interface WebhookService {

	Response webhookpaymentNotification(WebhookEventNotification notification) throws Exception;

}
