package com.letsfame.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsfame.bean.OrderRequest;
import com.letsfame.repository.OrderRepository;
import com.letsfame.response.ResponseDto;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@RestController()
public class OrderController {

	@Autowired
	private RazorpayClient razorpay;

	@Autowired
	private OrderRepository orderRequestRepo;

	ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping(path = "/txn_api/v1.0/create/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> order(@RequestBody final OrderRequest orderRequest) {

		ResponseDto res = new ResponseDto();
		OrderRequest resOrders = new OrderRequest();

		try {

			Order order = razorpay.orders.create(orderRequest.toJsonObject());
			System.out.println("order:::" + order);

			resOrders.setAmount(Double.parseDouble(order.get("amount").toString()));
			resOrders.setCurrency(order.get("currency"));
			resOrders.setReceipt(order.get("receipt"));
			resOrders.setOrderId(order.get("id"));
//			resOrders.setStatus(order.get("Status"));
			System.out.println("resOrders:::" + resOrders);
			resOrders = orderRequestRepo.save(resOrders);

			res.setData(resOrders);
			res.setStatus("Success");

		} catch (Exception e) {
			res.setStatus("Failed");
			res.setMessage(e.getMessage());
			System.out.println("Error while order :: Exception::" + ExceptionUtils.getStackTrace(e));

		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}
