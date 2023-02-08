//package com.letsfame.controller;
//
//import javax.validation.constraints.NotNull;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.letsfame.bean.Orders;
//import com.letsfame.response.ResponseDto;
//import com.letsfame.response.ResponseHandler;
//import com.letsfame.service.OrderService;
//
//import io.swagger.annotations.ApiOperation;
//import lombok.AllArgsConstructor;
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@AllArgsConstructor(onConstructor_ = { @Autowired })
//@RequestMapping("/txn_api/v1.0/subscription")
//public class OrderController {
//
//	@Autowired
//	private @NotNull OrderService orderService;
//
//	@PostMapping(value = "/create", produces = "application/json")
//	@ApiOperation(value = "Create/Update order Details")
//	public ResponseEntity<?> order(@RequestBody Orders req) {
//
//		try {
//
//			ResponseDto res = orderService.createOrder(req);
//
//			return ResponseHandler.generateResponse(HttpStatus.OK, res);
//		} catch (Exception e) {
//
//			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	@GetMapping(value = "/FetchAll", produces = "application/json")
//	@ApiOperation(value = "getall Orders Details")
//	public ResponseEntity<?> Orders() {
//
//		try {
//
//			ResponseDto res = orderService.getOrders();
//
//			return ResponseHandler.generateResponse(HttpStatus.OK, res);
//		} catch (Exception e) {
//
//			return ResponseHandler.errorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	}
//}
