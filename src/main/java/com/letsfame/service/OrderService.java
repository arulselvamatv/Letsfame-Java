package com.letsfame.service;


import com.letsfame.bean.OrderReq;
import com.letsfame.response.ResponseDto;

public interface OrderService {

	ResponseDto createOrder(OrderReq req);

	ResponseDto getOrders();

}
