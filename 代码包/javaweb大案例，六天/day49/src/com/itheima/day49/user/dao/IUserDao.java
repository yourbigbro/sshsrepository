package com.itheima.day49.user.dao;

import java.util.List;
import java.util.Map;

import com.itheima.day49.user.domain.Cart;
import com.itheima.day49.user.domain.Category;
import com.itheima.day49.user.domain.Orders;
import com.itheima.day49.user.domain.PageBean;
import com.itheima.day49.user.domain.Product;
import com.itheima.day49.user.domain.User;

public interface IUserDao {
	void register(User user);
	User login(User user);
	void active(User user);
	User findUserByCode(String code);
	List<Category> showClassify();
	List<Product> showHot();
	List<Product> showNew();
	List<Product> queryClassify(String cid, int startIndex, int pageSize);
	List<Product> queryAll(String cid);
	Product getProductByPid(String pid);
	void addIntoOrder(Cart cart, User user);
	void addIntoOrderItem(Cart cart);
	int getTotalNum(String uid);
	List<Orders> getAllMyOrder(String uid);
	List<Orders> getPageOrder(PageBean<Orders> pageBean,List<Orders> orders);
	Orders getOrderByOid(String oid);
	void addMessage(Map<String, String[]> pm);
	void modify(Orders order);
	List<Category> fillIn();
}
