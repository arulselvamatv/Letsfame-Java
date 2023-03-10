package com.letsfame.controller;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.LetsFameOrder;
import com.letsfame.request.OrderCreateRequest;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.OrderService;
import com.razorpay.Order;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/txn_api/v1.0/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/create", produces = "application/json")
	@ApiOperation(value = "Create order Details")
	public ResponseEntity<?> createOrder(@RequestBody OrderCreateRequest req) throws Exception {

		try {

			Order res = orderService.createOrder(req);

			return ResponseHandler.successGetResponse("Created successfully.", res.toJson().toMap(), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error :: createOrder :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/FetchAll", produces = "application/json")
	@ApiOperation(value = "getall Orders Details")
	public ResponseEntity<?> getOrders() throws Exception {

		try {

			List<LetsFameOrder> res = orderService.getOrders();

			return ResponseHandler.successGetResponse("Created successfully.", res, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error :: getOrders :: Exception :: " + ExceptionUtils.getStackTrace(e));
			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
