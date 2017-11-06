package com.itheima.day49.user.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.day49.user.dao.IUserDao;
import com.itheima.day49.user.domain.Cart;
import com.itheima.day49.user.domain.CartItem;
import com.itheima.day49.user.domain.Category;
import com.itheima.day49.user.domain.Orders;
import com.itheima.day49.user.domain.PageBean;
import com.itheima.day49.user.domain.Product;
import com.itheima.day49.user.domain.User;
import com.itheima.day49.user.utils.C3P0Utils;

public class UserDaoImpl implements IUserDao {

	@Override
	public void register(User user) {//注册
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		Object[] params={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User login(User user) {//登录
		String sql="select * from user where username=? and password=?";
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		Object[] params={user.getUsername(),user.getPassword()};
		try {
			return qr.query(sql, new BeanHandler<>(User.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void active(User user) {//激活之后改变数据库
		String sql="update user set state=?,code=? where username=?";
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		Object[] params={user.getState(),user.getCode(),user.getUsername()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserByCode(String code) {//查找激活码对应的对象
		String sql="select * from user where code=?";
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		Object[] params={code};
		try {
			return qr.query(sql, new BeanHandler<>(User.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Category> showClassify() {
		String sql="select * from category";
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				return qr.query(sql, new BeanListHandler<>(Category.class));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
	}

	@Override
	public List<Product> showHot() {
		String sql="select * from product where is_hot=? order by pdate limit ?";
		Object[] params={1,9};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				return qr.query(sql, new BeanListHandler<>(Product.class),params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public List<Product> showNew() {
		String sql="select * from product order by pdate limit ?";
		Object[] params={9};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				return qr.query(sql, new BeanListHandler<>(Product.class),params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	//三个参数分别为商品分类，该页的起始索引和每页的商品数(即页的大小)
	public List<Product> queryClassify(String cid, int startIndex, int pageSize) {
		String sql="select * from product where cid=? order by pdate limit ?,?";
		Object[] params={cid,startIndex,pageSize};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				return qr.query(sql, new BeanListHandler<>(Product.class),params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public List<Product> queryAll(String cid) {
		String sql="select * from product where cid=?";
		Object[] params={cid};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				return qr.query(sql, new BeanListHandler<>(Product.class),params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	public Product getProductByPid(String pid) {
		String sql="select * from product where pid=?";
		Object[] params={pid};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				return qr.query(sql, new BeanHandler<>(Product.class),params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	//向order表插入信息
	public void addIntoOrder(Cart cart,User user) {//uid代表该用户的主键（用户的唯一编号），oid代表订单编号
		String sql="insert into orders (oid,ordertime,total,state,uid) values (?,?,?,?,?)";
		//遍历计算商品的总数量
		List<CartItem> cartAll = cart.getCartAll();
		double allCount=0;//注意是double而不是int，有数据库可知。
		for (CartItem cartItem : cartAll) {
			allCount+=Integer.parseInt(cartItem.getCount());
		}
		Object[] params={cart.getNum(),cart.getDate(),allCount,0,user.getUid()};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			try {
				qr.update(sql,params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

	@Override
	//向orderItem表格插入信息
	public void addIntoOrderItem(Cart cart) {
		List<CartItem> cartAll = cart.getCartAll();
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());//创建DBUtils的核心类
		for (CartItem cartItem : cartAll) {//将订单中的各个订单项逐个插入数据库。其中subtotal指的是总金额
			String sql="insert into orderitem (itemid,count,subtotal,pid,oid) values (?,?,?,?,?)";
			Object[] params={cartItem.getItemId(),cartItem.getCount(),cartItem.getTotalPrice(),cartItem.getProduct().getPid(),cart.getNum()};
			try {
				qr.update(sql,params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		
	}
	//计算一个用户的订单总数（注意不是订单项总数）
	@Override
	public int getTotalNum(String uid) {
		String sql="select count(*) from orders where uid=?";
		Object[] params={uid};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		try {
			 return ((Long)qr.query(sql, new ScalarHandler(),params)).intValue();//注意将结果进行两次转换。并注意new ScalarHandler()的写法
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//用用户的uid查出用户的所有订单对象
	@Override
	public List<Orders> getAllMyOrder(String uid) {
		String sql="select * from orders where uid=?";
		Object[] params={uid};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				return qr.query(sql, new BeanListHandler<>(Orders.class),params);//Orders表中的User对象这一项并未被填充，因为从数据库中查出来的是uid而不是对象
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	//得到当前页的订单集合
	@Override
	public List<Orders> getPageOrder(PageBean<Orders> pageBean,List<Orders> orders) {
		int startIndex = pageBean.getStartIndex();
		int pageSize = pageBean.getPageSize();
		List<Orders> list=new ArrayList<Orders>();//不能初始化为null
		int num=0;
		for (Orders orders2 : orders) {
			if(num>=startIndex){
				list.add(orders2);
			}
			num++;
			if(num==(pageBean.getPageSize()+startIndex)){
				break;
			}
		}
		return list;
	}
	//根据订单编号得到订单对象
	@Override
	public Orders getOrderByOid(String oid) {
		String sql="select * from orders where oid=?";
		Object[] params={oid};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				return qr.query(sql, new BeanHandler<>(Orders.class),params);//Orders表中的User对象这一项并未被填充，因为从数据库中查出来的是uid而不是对象
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	//填写地址姓名电话
	@Override
	public void addMessage(Map<String, String[]> pm) {
		//得到订单编号
		String[] oid = pm.get("oid");
		String[] address = pm.get("address");
		String[] name = pm.get("name");
		String[] telephone = pm.get("telephone");
		String sql="update orders set address=?,name=?,telephone=? where oid=?";
		Object[] params={address,name,telephone,oid};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				qr.update(sql,params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	//根据iod修改orders表格中的state订单支付状态
	@Override
	public void modify(Orders order) {
		String sql="update orders set state=? where oid=?";
		Object[] params={order.getState(),order.getOid()};
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				qr.update(sql,params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		
	}
	//从category表中查询数据
	@Override
	public List<Category> fillIn() {
		String sql="select * from category";
		QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
		
			try {
				return qr.query(sql, new BeanListHandler<>(Category.class));//Orders表中的User对象这一项并未被填充，因为从数据库中查出来的是uid而不是对象
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

}
