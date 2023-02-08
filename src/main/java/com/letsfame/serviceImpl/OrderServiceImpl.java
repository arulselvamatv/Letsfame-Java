//package com.letsfame.serviceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.letsfame.bean.Orders;
//import com.letsfame.repository.OrderRepository;
//import com.letsfame.response.ResponseDto;
//import com.letsfame.service.OrderService;
//import com.razorpay.Order;
//import com.razorpay.RazorpayClient;
//
//@Service
//@Transactional
//public class OrderServiceImpl implements OrderService {
//
//	@Autowired
//	private OrderRepository orderRepository;
//
//	@Autowired
//	private RazorpayClient razorpay;
//
//	@Override
//	public ResponseDto createOrder(Orders req) {
//
//		ResponseDto response = new ResponseDto();
//		List<String> error = new ArrayList<>();
//		Orders resOrders = new Orders();
//		try {
//
//			Order order = razorpay.orders.create(req.toJsonObject());
//			System.out.println("order:::" + order);
//
//			resOrders.setAmount(Double.parseDouble(order.get("amount").toString()));
//			resOrders.setCurrency(order.get("currency"));
//			resOrders.setReceipt(order.get("receipt"));
//			resOrders.setOrderId(order.get("id"));
////			resOrders.setStatus(order.get("Status"));
//			System.out.println("resOrders:::" + resOrders);
//
//			resOrders = orderRepository.save(resOrders);
//
//			response.setData(resOrders);
//			response.setStatus("Success");
//			// return res;
//
//		} catch (Exception e) {
//
//			response.setStatus("Failed");
//
//			error.add(e.getMessage());
//			response.setMessages(error);
//			// response.getMessages().add(e.getMessage());
//
//			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));
//
//		}
//		return response;
//	}
//
//	@Override
//	public ResponseDto getOrders() {
//		ResponseDto response = new ResponseDto();
//		List<String> error = new ArrayList<>();
//		List<Orders> savedData = new ArrayList<Orders>();
//
//		try {
//
//			savedData = orderRepository.findAll();
//
//			response.setData(savedData);
//			response.setStatus("Success");
//		}
//
//		catch (Exception e) {
//
//			response.setStatus("Failed");
//
//			error.add(e.getMessage());
//			response.setMessages(error);
//			// response.getMessages().add(e.getMessage());
//
//			System.out.println("Error :: createPlan :: Exception::" + ExceptionUtils.getStackTrace(e));
//
//		}
//
//		return response;
//	}
//
//}
