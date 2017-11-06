package com.itheima.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.domain.Order;

public interface OrderDao extends JpaRepository<Order, Integer>{

}
