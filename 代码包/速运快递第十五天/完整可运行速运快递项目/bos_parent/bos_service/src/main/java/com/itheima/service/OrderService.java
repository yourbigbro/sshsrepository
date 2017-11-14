package com.itheima.service;

import javax.jws.WebService;

import com.itheima.domain.Order;

@WebService
public interface OrderService {

	void save(Order order);
}
