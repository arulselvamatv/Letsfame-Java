package com.letsfame.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.letsfame.bean.OrderRequest;

@RestController()
public class OrderController {
	@Autowired
	private RazorpayClient razorpayClient;

	@PostMapping(path = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> order(@RequestBody final OrderRequest orderRequest) {

		JSONObject message = new JSONObject();
		try {

			Order order = razorpayClient.orders.create(orderRequest.toJsonObject());
			message = order.toJson();
			System.out.println(message);

		} catch (RazorpayException e) {

			// Handle Exception
			message.put("error", e.getMessage());
			System.out.println(e.getMessage());

		}
		return new ResponseEntity<>(message.toString(), HttpStatus.OK);
	}

}
