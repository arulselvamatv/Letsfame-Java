package com.letsfame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.letsfame.bean.OrderReq;
import com.letsfame.response.ResponseDto;
import com.letsfame.response.ResponseHandler;
import com.letsfame.service.OrderService;

import io.swagger.annotations.ApiOperation;

@RestController()
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(path = "/txn_api/v1.0/create/orders")
	@ApiOperation(value = "Create/Update order Details")
	public ResponseEntity<?> order(@RequestBody OrderReq req) {

		try {

			ResponseDto res = orderService.createOrder(req);

			return ResponseHandler.generateResponse(HttpStatus.OK, res);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/txn_api/v1.0/fetchAllOrders")
	@ApiOperation(value = "getall Orders Details")
	public ResponseEntity<?> Orders() {

		try {

			ResponseDto res = orderService.getOrders();

			return ResponseHandler.generateResponse(HttpStatus.OK, res);
		} catch (Exception e) {

			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
