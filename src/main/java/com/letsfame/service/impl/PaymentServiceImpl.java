package com.letsfame.service.impl;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsfame.bean.Payment;
import com.letsfame.dto.PaginationDto;
import com.letsfame.repository.PaymentRepository;
import com.letsfame.request.PaymentUpdateRequest;
import com.letsfame.service.PaymentService;
import com.letsfame.util.DateUtils;
import com.razorpay.RazorpayClient;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private RazorpayClient razorpayClient;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Payment updatePaymentDetails(PaymentUpdateRequest req) throws Exception {

		Payment responseDataAndroid = new Payment();
		Payment responseDataIOS = new Payment();

		if ((req.getPaymentId() != null) && (req.getTransactionId() == null)) {
			com.razorpay.Payment payment = razorpayClient.payments.fetch(req.getPaymentId());

			if (payment != null) {
				responseDataAndroid = razorPayPaymentToLetsFamePayment(payment);

				Payment savedById = findByPaymentId(req.getPaymentId());

				logger.info("savedById:::{}", savedById);

				if (savedById != null) {
					responseDataAndroid.setId(savedById.getId());
				}
				paymentRepository.save(responseDataAndroid);
			}
			return responseDataAndroid;
		}

		if ((req.getTransactionId() != null) && (req.getPaymentId() == null)) {

			Payment savedByIdIOS = paymentRepository.findByTransactionId(req.getTransactionId());

			logger.info("savedByIdIOS:::{}", savedByIdIOS);
			if (savedByIdIOS != null) {
				responseDataIOS.setId(savedByIdIOS.getId());
			}

			responseDataIOS.setTransactionId(req.getTransactionId());
			responseDataIOS.setMemberId(req.getMemberId());
			responseDataIOS.setPlanId(req.getPlanId());
			responseDataIOS.setMonthCount(req.getMonthCount());
			responseDataIOS.setAmount(req.getAmount());
			responseDataIOS.setPayment(req.getPayment());
			responseDataIOS.setCreatedAt(req.getPlanCreatedDate());
			responseDataIOS.setExpireBy(req.getExpireBy());
			responseDataIOS.setPurchasedDevice(req.getPurchasedDevice());
			responseDataIOS.setRecurring(req.getRecurring());
			paymentRepository.save(responseDataIOS);
		}
		return responseDataIOS;
	}

	private Payment razorPayPaymentToLetsFamePayment(com.razorpay.Payment payment) {

		Payment paymentData = new Payment();

		JSONObject paymentJsonObject = payment.toJson();

		logger.info("payment:::{}", paymentJsonObject);

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
			paymentData.setCardId(paymentJsonObject.getString("card_id"));
		}
		if (!paymentJsonObject.isNull("bank")) {
			paymentData.setBank(paymentJsonObject.getString("bank"));
		}
		if (!paymentJsonObject.isNull("wallet")) {
			paymentData.setWallet(paymentJsonObject.getString("wallet"));
		}
		if (!paymentJsonObject.isNull("vpa")) {
			paymentData.setVpa(paymentJsonObject.getString("vpa"));
		}

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
	public PaginationDto findAllPayment(Integer pageNo, Integer pageSize, String sortBy) throws Exception {

		Pageable paging = PageRequest.of(pageNo, pageSize);

		Page<Payment> pagedResult = paymentRepository.findAll(paging);
		PaginationDto res = new PaginationDto();
		if (pagedResult.hasContent()) {
			res.setData(pagedResult.getContent());
			res.setPageNumber(pagedResult.getNumber());
			res.setPageSize(pagedResult.getTotalPages());
			res.setSorted(pagedResult.getSort().isSorted());
			res.setTotalElements(pagedResult.getTotalElements());
		}
		return res;

	}

	@Override
	public Payment findByPaymentId(String paymentId) throws Exception {
		Payment payment = new Payment();
		payment = paymentRepository.findByPaymentId(paymentId);
		if (payment == null) {
			payment = paymentRepository.findByTransactionId(paymentId);
		}
		return payment;
	}
}
