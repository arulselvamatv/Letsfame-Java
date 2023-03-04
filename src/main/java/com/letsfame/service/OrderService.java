package com.letsfame.service;

import java.util.List;

import com.letsfame.Req.LetsFameOrderReq;
import com.letsfame.bean.LetsFameOrder;
import com.razorpay.Order;

public interface OrderService {

	Order createOrder(LetsFameOrderReq req) throws Exception;

	List<LetsFameOrder> getOrders() throws Exception;
}
