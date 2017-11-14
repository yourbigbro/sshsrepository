package com.itheima.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.system.User;

public interface UserDao extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
