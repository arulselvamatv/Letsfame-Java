package com.letsfame.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsfame.bean.Payment;
import com.letsfame.dto.PaginationDto;
import com.letsfame.repository.PaymentRepository;
import com.letsfame.request.IosPaymentUpdateRequest;
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

	@Override
	public Payment updatePaymentDetails(PaymentUpdateRequest req) throws Exception {

		Payment responseData = new Payment();
		com.razorpay.Payment payment = razorpayClient.payments.fetch(req.getPaymentId());

		if (payment != null) {
			responseData = razorPayPaymentToLetsFamePayment(payment);

			Payment savedById = findByPaymentId(req.getPaymentId());

			System.out.println("savedById:::" + savedById);
			if (savedById != null) {
				responseData.setId(savedById.getId());
			}
			paymentRepository.save(responseData);
		}

		return responseData;
	}

	@Override
	public Payment iosUpdatePaymentDetails(IosPaymentUpdateRequest req) throws Exception {

		Payment responseData = new Payment();

		if (req != null) {

			Payment savedById = findByPaymentId(req.getPaymentId());

			System.out.println("savedById:::" + savedById);
			if (savedById != null) {
				responseData.setId(savedById.getId());
			}

			responseData.setPaymentId(req.getPaymentId());
			responseData.setMemberId(req.getMemberId());
			responseData.setPlanId(req.getPlanId());
			responseData.setMonthCount(req.getMonthCount());
			responseData.setAmount(req.getAmount());
			responseData.setPayment(req.getPayment());
			responseData.setCreatedAt(req.getPlanCreatedDate());
			responseData.setExpireBy(req.getExpireBy());
			responseData.setPurchasedDevice(req.getPurchasedDevice());
			responseData.setRecurring(req.getRecurring());
			paymentRepository.save(responseData);
		}

		return responseData;
	}

	private Payment razorPayPaymentToLetsFamePayment(com.razorpay.Payment payment) {

		Payment paymentData = new Payment();

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
		return paymentRepository.findByPaymentId(paymentId);
	}
}
