package com.letsfame.service;

import java.util.List;

import com.letsfame.bean.LetsFameOrder;
import com.letsfame.request.OrderCreateRequest;
import com.razorpay.Order;

public interface OrderService {

	Order createOrder(OrderCreateRequest req) throws Exception;

	List<LetsFameOrder> getOrders() throws Exception;
}
