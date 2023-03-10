package com.letsfame.serviceImpl;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsfame.bean.LetsFameOrder;
import com.letsfame.repository.OrderRepository;
import com.letsfame.request.OrderCreateRequest;
import com.letsfame.service.OrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RazorpayClient razorpay;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Order createOrder(OrderCreateRequest req) throws Exception {

		JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(req));

		Order order = razorpay.orders.create(jsonObject);
		System.out.println("order:::" + order);

		if (order != null) {

			LetsFameOrder savedData = razorPayOrderToLetsFameOrder(order);

			orderRepository.save(savedData);
		}

		return order;

	}

	private LetsFameOrder razorPayOrderToLetsFameOrder(Order order) {
		LetsFameOrder savedData1 = new LetsFameOrder();

		JSONObject orderJsonObject = order.toJson();
		savedData1.setOrderId(orderJsonObject.getString("id"));
		savedData1.setCurrency(orderJsonObject.getString("currency"));
		savedData1.setAmount(orderJsonObject.getDouble("amount"));
		savedData1.setReceipt(orderJsonObject.getString("receipt"));

		return savedData1;

	}

	@Override
	public List<LetsFameOrder> getOrders() throws Exception {

		return orderRepository.findAll();

	}

}
