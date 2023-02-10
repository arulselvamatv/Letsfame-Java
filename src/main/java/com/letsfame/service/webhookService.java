package com.letsfame.service;

import com.letsfame.response.Response;
import com.letsfame.webhook.PaymentDetailsWebhook;

public interface webhookService {

	Response getpayment(PaymentDetailsWebhook notification);
}
