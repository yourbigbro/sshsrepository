package com.itheima.day49.user.service.impl;

import java.util.List;
import java.util.Map;

import com.itheima.day49.user.dao.IUserDao;
import com.itheima.day49.user.dao.impl.UserDaoImpl;
import com.itheima.day49.user.domain.Cart;
import com.itheima.day49.user.domain.Category;
import com.itheima.day49.user.domain.Orders;
import com.itheima.day49.user.domain.PageBean;
import com.itheima.day49.user.domain.Product;
import com.itheima.day49.user.domain.User;
import com.itheima.day49.user.service.IUserService;
import com.itheima.day49.user.utils.BeanFactory;

public class UserServiceImpl implements IUserService {
	//创建dao层的对象供各个方法使用
	//IUserDao userdao=new UserDaoImpl();
	//使用工厂解决耦合问题
	IUserDao userdao=(IUserDao) BeanFactory.getBean("IUserDao");

	@Override
	public void register(User user) {//注册
		userdao.register(user);
		
	}

	@Override
	public User login(User user) {//登录
		
		return userdao.login(user);
	}

	@Override
	public void active(User user) {//激活之后修改数据库
		userdao.active(user);
		
	}

	@Override
	public User findUserByCode(String code) {//查找激活码对应的对象
		return userdao.findUserByCode(code);
	}

	@Override
	public List<Category> showClassify() {
		
		return userdao.showClassify();
	}

	@Override
	public List<Product> showHot() {
		
		return userdao.showHot();
	}

	@Override
	public List<Product> showNew() {
		return userdao.showNew();
	}

	@Override
	public PageBean<Product> queryClassify(String cid,int pageNumber) {
		//分页.pageNumber和pageSize已经在pageBean里面设置了。
		PageBean<Product> pageBean = new PageBean<>();
		//查询某种分类下的商品的总数量
		List<Product> allProducts=userdao.queryAll(cid);
		pageBean.setTotal(allProducts.size());
		pageBean.setPageNumber(pageNumber);
		pageBean.setPageSize(12);
		List<Product> products=userdao.queryClassify(cid,pageBean.getStartIndex(),pageBean.getPageSize());//三个参数分别是商品分类，商品起始索引和每页的商品数
		pageBean.setRows(products);
		
		//至上一行为止pageBean对象已经完整了
		return pageBean;
	}

	@Override
	//根据pid查找商品对象
	public Product getProductByPid(String pid) {
		return userdao.getProductByPid(pid);
	}

	@Override
	//存储还未付款的订单信息
	public void setNewOrder(Cart cart,User user) {
		//向order表格插入信息
		userdao.addIntoOrder(cart,user);
		//向orderItem表格插入信息
		userdao.addIntoOrderItem(cart);
	}
	//计算出一个人的订单总数（注意不是订单项总数）
	@Override
	public int getTotalNum(String uid) {
		return userdao.getTotalNum(uid);
	}
	//根据uid查出某个人的所有订单对象
	@Override
	public List<Orders> getAllMyOrder(String uid) {
		return userdao.getAllMyOrder(uid);
	}

	@Override
	public List<Orders> getPageOrder(PageBean<Orders> pageBean,List<Orders> orders) {
		return userdao.getPageOrder(pageBean,orders);
	}
	//根据订单编号查询订单对象
	@Override
	public Orders getOrderByOid(String oid) {
		return userdao.getOrderByOid(oid);
	}
	//填写地址电话姓名等信息
	@Override
	public void addMessage(Map<String, String[]> pm) {
		userdao.addMessage(pm);
		
	}
	//根据iod修改state值
	@Override
	public void modify(Orders order) {
		userdao.modify(order);
		
	}
	//从category表中查询数据
	@Override
	public List<Category> fillIn() {
		return userdao.fillIn();
	}

	

}
