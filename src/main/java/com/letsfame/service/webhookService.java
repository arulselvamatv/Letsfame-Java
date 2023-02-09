package com.letsfame.service;

import com.letsfame.response.Response;
import com.letsfame.webhook.paymentDetailsWebhook;

public interface webhookService {

	Response getpayment(paymentDetailsWebhook notification);
}
