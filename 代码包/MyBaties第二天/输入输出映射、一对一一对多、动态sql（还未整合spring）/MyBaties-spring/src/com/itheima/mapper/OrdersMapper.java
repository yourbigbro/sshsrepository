package com.itheima.mapper;

import java.util.List;

import com.itheima.domain.Orders;

public interface OrdersMapper {

	List<Orders> findAllOrders();
}
