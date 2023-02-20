package com.letsfame.service;

import java.util.List;

import com.letsfame.dto.PaymentDetailsWebhookDto;
import com.letsfame.response.Response;

public interface webhookService {

	Response webhookpaymentNotification(PaymentDetailsWebhookDto notification);

	List<PaymentDetailsWebhookDto> getWebhookNotification();
}
