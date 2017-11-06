package com.itheima.day49.user.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.google.gson.Gson;
import com.itheima.day49.user.domain.Cart;
import com.itheima.day49.user.domain.CartItem;
import com.itheima.day49.user.domain.Category;
import com.itheima.day49.user.domain.OrderItem;
import com.itheima.day49.user.domain.Orders;
import com.itheima.day49.user.domain.PageBean;
import com.itheima.day49.user.domain.Product;
import com.itheima.day49.user.domain.User;
import com.itheima.day49.user.service.IUserService;
import com.itheima.day49.user.service.impl.UserServiceImpl;
import com.itheima.day49.user.utils.BaseServlet;
import com.itheima.day49.user.utils.BeanFactory;
import com.itheima.day49.user.utils.C3P0Utils;
import com.itheima.day49.user.utils.JedisUtils;
import com.itheima.day49.user.utils.MailUtils;
import com.itheima.day49.user.utils.PaymentUtil;
import com.itheima.day49.user.utils.UUIDUtils;

import redis.clients.jedis.Jedis;
//这个Servlet里面没有service方法，不能处理，页面的请求到达的时候他要先从父类里面查找service方法。
@WebServlet(name="UserServlet",urlPatterns="/UserServlet")
public class UserServlet extends BaseServlet{//继承工具包里面的基础类
	//private IUserService service=new UserServiceImpl();
	//使用工厂解决耦合问题
	private IUserService service=(IUserService) BeanFactory.getBean("IUserService");
	//注册
	public String register(HttpServletRequest request,HttpServletResponse response){
		User user = new User();
		try {
			BeanUtils.populate(user,request.getParameterMap());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}//为user对象的成员变量赋值
		user.setCode(UUIDUtils.getUUID64());//设置激活码
		user.setUid(UUIDUtils.getUUID());//设置主键
		user.setState(0);//是否已被激活。当前未激活。
		service.register(user);//调用dao层
		MailUtils.sendMail(user.getEmail(), user.getCode());//第一个参数是接收者的邮箱地址，第二个参数是要发送的内容（这里是激活码）
		request.setAttribute("msg","<h3 style='color:red'>注册成功，请立即前往邮箱激活</h3>");
		return "msg.jsp";//注册成功之后跳转到消息页面，提醒用户前往邮箱进行激活
	}
	//登录
	public String login(HttpServletRequest request,HttpServletResponse response){
		User user = new User();
		try {
			BeanUtils.populate(user,request.getParameterMap());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}//为user对象的成员变量赋值
		user=service.login(user);//登陆之后返回的完整对象
		if(user!=null){
			request.getSession().setAttribute("user", user);//登陆后要将对象保存到session
			request.setAttribute("user", user);
			return "index.jsp";//登陆成功之后跳转到首页
		}else{
			return "login.jsp";
		}
	}
	//激活
	public String active(HttpServletRequest request,HttpServletResponse response){
		String code=request.getParameter("code");
		User user=service.findUserByCode(code);//输入激活码返回的是某个用户对象（假如该激活码存在的话）
		if(user!=null){
			user.setCode("");//使激活码失效，防止再次激活
			user.setState(1);//将激活状态设置为已经激活
			service.active(user);//将信息封装到javabean对数据库进行修改。上面设置了属性不代表修改了数据库
			return "login.jsp";//激活之后要到登录页面而不是直接到登陆成功的页面（也就是首页index.jsp）
		}else {
			request.setAttribute("msg","<h3 style='color:red'>你已经激活过了，不要再重复激活</h3>");
			return "msg.jsp";//将信息设置到request之中之后肯定会链接到消息页面
		}
	}
	//退出
	public String logout(HttpServletRequest request,HttpServletResponse response){
		request.getSession().removeAttribute("user");//销毁session。注意删除属性用removeattribute
		return "login.jsp";
	}
	//异步加载商品分类信息
	public String showClassify(HttpServletRequest request,HttpServletResponse response){
		Jedis jedis = JedisUtils.getJedis();
		String data = jedis.get("categorys");//从redis缓存中读取数据
		if(data==null){
			List<Category> category=service.showClassify();
			Gson gson = new Gson();
			data=gson.toJson(category);
			jedis.set("categorys", data);//将新读取的数据存入redis缓存
		}
		try {
			response.getWriter().write(data);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	//异步加载热门商品
	public String showHot(HttpServletRequest request,HttpServletResponse response){
		List<Product> products=service.showHot();
		Gson gson = new Gson();
		String json=gson.toJson(products);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	//异步加载最新商品
		public String showNew(HttpServletRequest request,HttpServletResponse response){
			List<Product> products=service.showNew();
			Gson gson = new Gson();
			String json=gson.toJson(products);
			try {
				response.getWriter().write(json);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
		}
		//分类分页加载商品
		public String queryClassify(HttpServletRequest request,HttpServletResponse response){
			String cid = request.getParameter("cid");
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));//获得页数并强转
			PageBean<Product> pageBean=service.queryClassify(cid,pageNumber);//查得某个分类下所有的商品对象并在service层进行分类和分页封装在pageBean里
			request.setAttribute("pageBean", pageBean);
			System.out.println(pageBean.getRows());//pageBean有问题
			return "product_list.jsp";
		}
		//根据pid查找商品
		public String getProductByPid(HttpServletRequest request,HttpServletResponse response){
			String pid = request.getParameter("pid");
			Product product=service.getProductByPid(pid);
			request.setAttribute("product", product);//将product对象装入域对象
			return  "product_info.jsp";
		}
		//将商品加入购物车
		public String fillCart(HttpServletRequest request,HttpServletResponse response){
			String pid = request.getParameter("pid");
			String count = request.getParameter("count");
			Product product=service.getProductByPid(pid);//得到product对象
			CartItem cartItem = new CartItem();//创建单个商品的购物车对象
			cartItem.setCount(count);
			cartItem.setItemId(UUIDUtils.getUUID());
			cartItem.setProduct(product);
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			if(cart==null){
				cart = new Cart();//创建一个新的购物车对象
				List<CartItem> list=new ArrayList<>();
				list.add(cartItem);
				cart.setCartAll(list);
				//将cart写到session，保存到session中是因为购物车内的信息要较长时间的保存。
				request.getSession().setAttribute("cart",cart);
			}else {//有cart session的情况，意味着可能出现重复商品
				List<CartItem> list=cart.getCartAll();
				boolean flag=false;
				for (CartItem cartItem2 : list) {
					if(pid.equals(cartItem2.getProduct().getPid())){
						cartItem2.setCount(""+(Integer.parseInt(cartItem2.getCount())+Integer.parseInt(count)));//对购物车内的单个商品对象进行更新
						flag=true;
						break;
					}
				}
				if(flag==false){//只有当以前没有这一项时才会将新的一项加入集合
					list.add(cartItem);
				}
				cart.setCartAll(list);
				cart.setDate(new Date());
				cart.setState(0);
				cart.setNum(UUIDUtils.getUUID());
				cart.setUser((User)request.getSession().getAttribute("user"));//从提交订单的页面cart.jsp获取用户信息并填写到user对象
				cartItem.setCart(cart);//至此cartItem就完整了
				//将cart写到session
				request.getSession().setAttribute("cart",cart);//回写session。其中所有种类的商品的总价格已经在Cart类内部计算完毕了。
			}
			try {
				response.sendRedirect(request.getContextPath()+"/cart.jsp");//重定向
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
			
			
		}
		//删除购物车中的单个商品。不用修改数据库，因为存储在session中
		public String deleteCart(HttpServletRequest request,HttpServletResponse response){
			String pid = request.getParameter("pid");
			Cart cart = (Cart)request.getSession().getAttribute("cart");//从session中取得购物车信息
			List<CartItem> cartAll = cart.getCartAll();//得到集合
				for (CartItem cartItem : cartAll) {
					if(cartItem.getProduct().getPid().equals(pid)){//从购物车删除指定pid的商品
						cartAll.remove(cartItem);
						break;
					}
				}
			cart.setCartAll(cartAll);
			request.getSession().setAttribute("cart",cart);//回写session
			try {
				response.sendRedirect(request.getContextPath()+"/cart.jsp");//重定向
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
		}
		//清空购物车。
		public String deleteAllCart(HttpServletRequest request,HttpServletResponse response){
			Cart cart = (Cart)request.getSession().getAttribute("cart");//从session中取得购物车信息
			List<CartItem> cartAll = cart.getCartAll();//得到集合
			cartAll.clear();
			cart.setCartAll(cartAll);
			request.getSession().setAttribute("cart",cart);//回写session
			try {
				response.sendRedirect(request.getContextPath()+"/cart.jsp");//重定向
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
		}
		//提交订单进入订单详情页面并在数据库创建未付款状态的订单
		public String saveCart(HttpServletRequest request,HttpServletResponse response){
			//session中已经有订单的信息了(仍然是购物车中的信息)，这里只需要将信息存到数据库并跳转到到订单详情页面
			User user = (User)request.getSession().getAttribute("user");
			//检测用户是否已经登录，未登录的话不允许提交订单，尽管还未付款
			if(user==null){
				request.setAttribute("msg", "请先登录再提交订单");
				return "msg.jsp";
			}
			//用户已经登录，可以提交订单并操作数据库
			//将信息填进数据库
			Cart cart = (Cart)request.getSession().getAttribute("cart");//从session中获取购物车中的数据
			//进入service层修改数据库
			service.setNewOrder(cart,user);
			try {
				response.sendRedirect(request.getContextPath()+"/order_info.jsp");//重定向
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
		}
		//查询我的订单
		public String myOrder(HttpServletRequest request,HttpServletResponse response){
			String pageNumber = request.getParameter("pageNumber");
			PageBean<Orders> pageBean = new PageBean<Orders>();//在这里将泛型赋予具体的类型
			pageBean.setPageNumber(Integer.parseInt(pageNumber));
			pageBean.setPageSize(3);
			
			//根据用户的主键uid查询他所有的订单以及该订单的信息
			User user =(User) request.getSession().getAttribute("user");
			String uid = user.getUid();
			//根据用户的主键uid得出一个人的订单总数
			int totalNum=service.getTotalNum(user.getUid());
			pageBean.setTotal(totalNum);
			
			//根据用户主键查到他所有的订单对象Orders
			List<Orders> orders=service.getAllMyOrder(uid);//唯有User对象的那个成员变量未被填充。区分这个list<Orders>和下面分页的list<Orders>
			
			//分别设置orders对象中的list属性
			for (Orders orders2 : orders) {//遍历每一个订单
				
				ArrayList<OrderItem> list =new ArrayList<OrderItem>();
				String sql="select * from product p,orderitem o where o.pid=p.pid and oid=?";
				Object[] params={orders2.getOid()};
				QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
				List<Map<String, Object>> query=null;
				try {
					query = qr.query(sql, new MapListHandler(), params);//注意只有一个圆括号
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				for (Map<String, Object> map : query) {
					Product product=new Product();//注意不是null而是new对象！！！！！！！！！！！！！！！！！因为既然填充就要是壳子而不能是什么都没有
					OrderItem orderitem=new OrderItem();//注意不是null而是new对象！！！！！！！！！！！！！！！！！
					try {
						BeanUtils.populate(product, map);//装填orderitem中的product成员变量
						System.out.println(product.getPimage());
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}
					try {
						BeanUtils.populate(orderitem, map);//装填orders中的orderitem成员变量
					} catch (IllegalAccessException e) {
						throw new RuntimeException(e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(e);
					}
					//System.out.println("遍历");成功
					orderitem.setProduct(product);//继续装填orderitem成员变量
					
					list.add(orderitem);//最终装填orders订单对象的成员变量，使得每一个订单都包含了订单项集合
				}
				orders2.setList(list);
			}
			
			//得到当页的订单集合以填充(赋值)到pageBean中（即找到一个人的部分订单，即我的订单的某一页）
			List<Orders> pageOrders=service.getPageOrder(pageBean,orders);
			pageBean.setRows(pageOrders);
			request.setAttribute("pageBean",pageBean);
			return "order_list.jsp";
		}
		//确认订单并且填写地址，收货人，电话，并且选择用哪个银行付款(订单支付)
		public String payOrder(HttpServletRequest request,HttpServletResponse response){
			String oid = request.getParameter("oid");
			//根据订单编号得到订单对象(一个对象)
			Orders orders2=service.getOrderByOid(oid);
			
			//从上面的方法中复制粘贴的
			//用product填充orderitem，用orderitem填充orders对象中的list集合。只需要一次遍历
			ArrayList<OrderItem> list =new ArrayList<OrderItem>();
			//计算某订单的总金额并存储到域对象
			int payMoney = 0;
			String sql="select * from product p,orderitem o where o.pid=p.pid and oid=?";
			Object[] params={orders2.getOid()};
			QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
			List<Map<String, Object>> query=null;
			try {
				query = qr.query(sql, new MapListHandler(), params);//注意只有一个圆括号
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			for (Map<String, Object> map : query) {
				Product product=new Product();//注意不是null而是new对象！！！！！！！！！！！！！！！！！因为既然填充就要是壳子而不能是什么都没有
				OrderItem orderitem=new OrderItem();//注意不是null而是new对象！！！！！！！！！！！！！！！！！
				try {
					BeanUtils.populate(product, map);//装填orderitem中的product成员变量
					System.out.println(product.getPimage());
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				try {
					BeanUtils.populate(orderitem, map);//装填orders中的orderitem成员变量
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
				//System.out.println("遍历");成功
				orderitem.setProduct(product);//继续装填orderitem成员变量
				payMoney+=orderitem.getSubtotal();
				list.add(orderitem);//最终装填orders订单对象的成员变量，使得每一个订单都包含了订单项集合
			}
			orders2.setList(list);
			
			
			//将orders对象装入域对象并转发
			request.setAttribute("orders2",orders2);
			request.setAttribute("payMoney",payMoney);
			return "order_info1.jsp";
		}
		//填写地址并确认订单
		public String verifyOrder(HttpServletRequest request,HttpServletResponse response){
			Map<String, String[]> pm = request.getParameterMap();
			//根据得到的信息在数据库中插入地址名字电话信息
			service.addMessage(pm);
			
			//根据oid得到orders对象
			String oid = request.getParameter("oid");
			Orders order = service.getOrderByOid(oid);
			
			//支付(复制粘贴代码)
			String p0_Cmd = "Buy";
			String p1_MerId = "10001126856";
			String p2_Order = order.getOid();
			String p3_Amt = "0.01";//测试用1分钱，真正开发中用order.getTotal();
			String p4_Cur = "CNY";
			String p5_Pid = "";
			String p6_Pcat = "";
			String p7_Pdesc = "";
			String p8_Url = "http://localhost:8080"+request.getContextPath()+"/UserServlet?type=payOrderSuccess";//规定接受支付结果的Servlet页面
			String p9_SAF = "0";
			String pa_MP = "";
			String pd_FrpId = request.getParameter("pd_FrpId");
			String pr_NeedResponse = "1";
			String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");

			StringBuffer buffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
			buffer.append("p0_Cmd="+p0_Cmd);
			buffer.append("&p1_MerId="+p1_MerId);
			buffer.append("&p2_Order="+p2_Order);
			buffer.append("&p3_Amt="+p3_Amt);
			buffer.append("&p4_Cur="+p4_Cur);
			buffer.append("&p5_Pid="+p5_Pid);
			buffer.append("&p6_Pcat="+p6_Pcat);
			buffer.append("&p7_Pdesc="+p7_Pdesc);
			buffer.append("&p8_Url="+p8_Url);
			buffer.append("&p9_SAF="+p9_SAF);
			buffer.append("&pa_MP="+pa_MP);
			buffer.append("&pd_FrpId="+pd_FrpId);
			buffer.append("&pr_NeedResponse="+pr_NeedResponse);
			buffer.append("&hmac="+hmac);

			try {
				response.sendRedirect(buffer.toString());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;
			
		}
		//接收支付成功失败的结果
		public String payOrderSuccess(HttpServletRequest request,HttpServletResponse response){
			
			//获取返回的所有参数
			String p1_MerId = request.getParameter("p1_MerId");
			String r0_Cmd = request.getParameter("r0_Cmd");
			String r1_Code = request.getParameter("r1_Code");
			String r2_TrxId = request.getParameter("r2_TrxId");
			String r3_Amt = request.getParameter("r3_Amt");
			String r4_Cur = request.getParameter("r4_Cur");
			String r5_Pid = request.getParameter("r5_Pid");
			String r6_Order = request.getParameter("r6_Order");
			String r7_Uid = request.getParameter("r7_Uid");
			String r8_MP = request.getParameter("r8_MP");
			String r9_BType = request.getParameter("r9_BType");
			String rb_BankId = request.getParameter("rb_BankId");
			String ro_BankOrderId = request.getParameter("ro_BankOrderId");
			String rp_PayDate = request.getParameter("rp_PayDate");
			String rq_CardNo = request.getParameter("rq_CardNo");
			String ru_Trxtime = request.getParameter("ru_Trxtime");
			String hmac = request.getParameter("hmac");

			//校验数据是否正确
			boolean flag = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
			if(flag){
				//数据正确,修改订单状态，即支付成功之后修改订单的支付状态为1
				try{
					Orders order = service.getOrderByOid(r6_Order);
					order.setState(1);
					service.modify(order);//根据order中的oid来更新orders表格中的status状态
					request.setAttribute("msg","订单付款成功,订单号为:"+r6_Order+"///付款金额为:"+r3_Amt);
				}catch(Exception e){
					throw new RuntimeException(e);
				}
				
			}else{
				throw new RuntimeException("数据遭篡改");
			}
			return "msg.jsp";
			
			
		}
		//查询数据库信息并填充到面板中
		public String fillIn(HttpServletRequest request,HttpServletResponse response){
			List<Category> categories=service.fillIn();//从category表中查询数据并填入category对象中
			Gson gson = new Gson();
			String json = gson.toJson(categories);
			try {
				response.getWriter().write(json);//将json数据返回发送请求的页面
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return null;//这个返回值是转发用的。假如重定向或者再返回本页面的话就要return null。
		}
	}
