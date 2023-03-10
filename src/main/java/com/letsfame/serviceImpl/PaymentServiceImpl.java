package com.letsfame.serviceImpl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsfame.bean.LetsFamePayment;
import com.letsfame.repository.PaymentRepository;
import com.letsfame.request.PaymentUpdateRequest;
import com.letsfame.service.PaymentService;
import com.letsfame.util.DateUtils;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private RazorpayClient razorpayClient;

	@Override
	public Payment updatePaymentDetails(PaymentUpdateRequest req) throws Exception {

		Payment payment = razorpayClient.payments.fetch(req.getPaymentId());

		if (payment != null) {
			LetsFamePayment responseData = razorPayPaymentToLetsFamePayment(payment);
			paymentRepository.save(responseData);
		}

		return payment;
	}

	private LetsFamePayment razorPayPaymentToLetsFamePayment(Payment payment) {

		LetsFamePayment paymentData = new LetsFamePayment();

		JSONObject paymentJsonObject = payment.toJson();

		System.out.println("payment:::" + paymentJsonObject);

		paymentData.setPaymentId(paymentJsonObject.getString("id"));
		paymentData.setEntity(paymentJsonObject.getString("entity"));
		paymentData.setAmount(paymentJsonObject.getDouble("amount"));
		paymentData.setCurrency(paymentJsonObject.getString("currency"));
		paymentData.setOrderId(paymentJsonObject.getString("order_id"));
		paymentData.setInvoiceId(paymentJsonObject.getString("invoice_id"));
		paymentData.setInternational(paymentJsonObject.getBoolean("international"));
		paymentData.setMethod(paymentJsonObject.getString("method"));
		paymentData.setAmount_refunded(paymentJsonObject.getDouble("amount_refunded"));
		paymentData.setCaptured(paymentJsonObject.getBoolean("captured"));
		if (!paymentJsonObject.isNull("description")) {
			paymentData.setDescription(paymentJsonObject.getString("description"));
		}
		if (!paymentJsonObject.isNull("card_id")) {
			paymentData.setCardId(paymentJsonObject.getJSONObject("card_id"));
		}
		if (!paymentJsonObject.isNull("bank")) {
			paymentData.setBank(paymentJsonObject.getString("bank"));
		}
		if (!paymentJsonObject.isNull("wallet")) {
			paymentData.setWallet(paymentJsonObject.getString("wallet"));
		}
		paymentData.setVpa(paymentJsonObject.getString("vpa"));
		paymentData.setEmail(paymentJsonObject.getString("email"));
		paymentData.setContact(paymentJsonObject.getString("contact"));
		paymentData.setCustomerId(paymentJsonObject.getString("customer_id"));
		paymentData.setTokenId(paymentJsonObject.getString("token_id"));

		if (!paymentJsonObject.isNull("created_at")) {
			paymentData.setCreatedAt(DateUtils.getRazorPayTimeStamp(paymentJsonObject.getInt("created_at")));
		}

		return paymentData;

	}

	@Override
	public List<LetsFamePayment> findAllPayment() throws Exception {

		return paymentRepository.findAll();

	}

	@Override
	public LetsFamePayment findByPaymentId(String paymentId) throws Exception {
		return paymentRepository.findByPaymentId(paymentId);
	}
}
