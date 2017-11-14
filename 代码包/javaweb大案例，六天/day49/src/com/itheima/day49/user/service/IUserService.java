package com.itheima.day49.user.service;

import java.util.List;
import java.util.Map;

import com.itheima.day49.user.domain.Cart;
import com.itheima.day49.user.domain.Category;
import com.itheima.day49.user.domain.Orders;
import com.itheima.day49.user.domain.PageBean;
import com.itheima.day49.user.domain.Product;
import com.itheima.day49.user.domain.User;

public interface IUserService {
	void register(User user);
	User login(User user);
	void active(User user);
	User findUserByCode(String code);
	List<Category> showClassify();
	List<Product> showHot();
	List<Product> showNew();
	PageBean<Product> queryClassify(String cid,int  pageNumber);
	Product getProductByPid(String pid);
	void setNewOrder(Cart cart, User user);
	int getTotalNum(String uid);
	List<Orders> getAllMyOrder(String uid);
	List<Orders> getPageOrder(PageBean<Orders> pageBean,List<Orders> orders);
	Orders getOrderByOid(String oid);
	void addMessage(Map<String, String[]> pm);
	void modify(Orders order);
	List<Category> fillIn();
}
