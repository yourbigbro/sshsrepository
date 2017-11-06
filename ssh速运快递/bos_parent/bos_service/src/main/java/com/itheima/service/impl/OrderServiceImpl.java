package com.itheima.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.Customer;
import com.itheima.customerService.CustomerService;
import com.itheima.dao.OrderDao;
import com.itheima.domain.Area;
import com.itheima.domain.Courier;
import com.itheima.domain.FixedArea;
import com.itheima.domain.Order;
import com.itheima.domain.SubArea;
import com.itheima.domain.WorkBill;
import com.itheima.service.AreaService;
import com.itheima.service.FixedAreaService;
import com.itheima.service.OrderService;
import com.itheima.service.SubAreaService;
import com.itheima.service.WorkBillService;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private SubAreaService subAreaService;
	@Autowired
	private FixedAreaService fixedAreaService;
	@Autowired
	private WorkBillService workBillService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private OrderDao orderDao;

	//该方法用于保存订单(这是用户填写的单子)
	//注意，这个order对象是前台传进来的模型驱动封装的，而不是数据库查询出来的持久化对象，因此有些属性是没有的，所以在需要的时候应该再在数据库中查询一次，以获得持久化对象
	@Override
	public void save(Order order) {
		System.out.println("已经进入了orderservice");

		String address=order.getSendAddress();
		//根据前台传来的发件人详细地址精确匹配查找相应的用户对象customer
		//service层可以直接写dao层，因此可以写成customerDao.findByAddress(address)。但是感觉不规范
		//该方法用的是crm服务端的服务，需要依赖domain_customer项目(因为他是crm服务的接收端)
		List<Customer> list=customerService.findByAddress(address);
		//该customer为持久化对象
		Customer customer=list.get(0);
		
		if(customer!=null){
			//假如找到了对应的用户，就可以由用户直接找到定区id和定区以及定区对应的快递员集合(courier集合)
			String fixedAreaId = customer.getFixedAreaId();
			//该fixedArea对象也是持久化对象
			//注意，fixedAreaId变量不能是null，否则会报The given id must not be null!的错误
			FixedArea findOne = fixedAreaService.findOne(fixedAreaId);
			//这些快递员对象是持久化对象
			Set<Courier> couriers = findOne.getCouriers();
			//注意couriers不能为null，否则下面的遍历会报错
			for (Courier courier : couriers) {
				//挑选一个满足某些条件的快递员，这里就简写了
				if(true){
					//设置订单(至此订单只剩下工单集合对象没有写，其他的都全了。下面写工单对象。工单设置完毕之后订单才可以设置完毕，所以要一起save才行)
					order.setCourier(courier);
					//将不需要的非持久化对象设置为null，因为两个对象是不能同时保存的，也就是order对象中作为属性的对象必须是持久对象
					order.setRecArea(null);
					order.setSendArea(null);
					//注意，要将订单编号转化为string类型。UUID.randomUUID()应该既不是字符串类型也不是integer或double类型
					order.setOrderNum(UUID.randomUUID().toString());
					order.setSendMobileMsg("快递员辛苦了");
					//设置为当前时间
					order.setOrderTime(new Date());
					//创建工单对象并且完善工单中的各种信息
					//该工单对象是自己创建的对象，不是持久化对象，缺少很多属性
					WorkBill workBill=new WorkBill();
					workBill.setCourier(courier);
					//完善工单中的其他内容
					workBill.setType("新");
					workBill.setBuildtime(new Date());
					workBill.setAttachbilltimes(0);
					workBill.setOrder(order);
					
					//将工单集合放入订单对象
						//创建工单集合并将工单放入该集合
						Set<WorkBill> set=new HashSet<>();
						set.add(workBill);
						//将工单集合赋值为order的属性
						order.setWorkBills(set);
					//保存订单对象和工单对象
					//这两个对象谁先保存谁后保存都是一样的，因为由于他们属于一个事务，他们是同时保存的。
					workBillService.save(workBill);
					System.out.println("保存order。打印order为："+order);
					orderDao.save(order);
					//注意他是直接结束方法而不只是跳出循环
					return;
				}
			}
		}else {
			//假如没找到对应的用户，就要从分区表中根据主要关键字和次要关键字查找对应的分区，进而得到分区对应的唯一定区
			//假如也没查找到对应的分区，那么就要进入人工分单，人工确定快递员
			//从订单对象order中获得区域对象
			//注意，因为order是非持久化对象，所以area也是非持久化对象。因为前台并没有传入区域的分区集合信息，所以要先将区域对象持久化再从里面查找分区对象集合
			//注意，数据库表中好像没有外键，例如区域对象的表中就没有分区集合
			//先获得area对象然后在进行持久化而不是直接进行持久化的原因都是想获得省市区的字符串作为参数
			Area area = order.getSendArea();
			//覆盖原来的area对象，使它变为持久化对象(这个jpa方法返回集合或者单个对象都行，虽然应该是集合但是查询结果只有一个也可以不写集合)
			area= areaService.findByProvinceAndCityAndDistrict(area.getProvince(),area.getCity(),area.getDistrict());
			//获取区域下面的所有分区(由于前台没有传入分区信息所以必须从持久化对象里面查询)
			Set<SubArea> subareas = area.getSubareas();
			for (SubArea subArea : subareas) {
				//假如找到了符合条件的分区对象
				if(address.contains(subArea.getKeyWords())||address.contains(subArea.getAssistKeyWords())){
					//根据分区得到定区对象
					FixedArea fixedArea = subArea.getFixedArea();
					//根据定区对象得到快递员集合。下面的就和上面的业务逻辑一模一样了
					Set<Courier> couriers = fixedArea.getCouriers();
					
					for (Courier courier : couriers) {
						//挑选一个满足某些条件的快递员，这里就简写了
						if(true){
							//设置订单(至此订单只剩下工单集合对象没有写，其他的都全了。下面写工单对象。工单设置完毕之后订单才可以设置完毕，所以要一起save才行)
							order.setCourier(courier);
							//将不需要的非持久化对象设置为null，因为两个对象是不能同时保存的，也就是order对象中作为属性的对象必须是持久对象
							order.setRecArea(null);
							order.setSendArea(null);
							//注意，要将订单编号转化为string类型。UUID.randomUUID()应该既不是字符串类型也不是integer或double类型
							order.setOrderNum(UUID.randomUUID().toString());
							order.setSendMobileMsg("快递员辛苦了");
							//设置为当前时间
							order.setOrderTime(new Date());
							//创建工单对象并且完善工单中的各种信息
							WorkBill workBill=new WorkBill();
							workBill.setCourier(courier);
							//完善工单中的其他内容
							workBill.setType("新");
							workBill.setBuildtime(new Date());
							workBill.setAttachbilltimes(0);
							workBill.setOrder(order);
							
							//将工单集合放入订单对象
								//创建工单集合并将工单放入该集合
								Set<WorkBill> set=new HashSet<>();
								set.add(workBill);
								//将工单集合赋值为order的属性
								order.setWorkBills(set);
							//保存订单对象和工单对象
							workBillService.save(workBill);
							System.out.println("保存order。打印order为："+order);
							orderDao.save(order);
							//找到了符合条件的分区对象的话就直接跳出循环并且结束方法
							return;
						}
					}
					
				}
			}
			
		}
		
		//假如上面的if else里面的return执行了的话该方法就已经结束了，也就是不会执行到这里。假如上面两个分支都没有执行到的话就会执行这个，也就是人工分配快递员
		//人工分配除了人工分配快递员之外和上面的两个分支没有任何区别
		Courier courier =new Courier();
		order.setCourier(courier);
		//创建工单对象并且完善工单中的各种信息
		WorkBill workBill=new WorkBill();
		workBill.setCourier(courier);
		//完善工单中的其他内容
		workBill.setType("新");
		workBill.setBuildtime(new Date());
		workBill.setAttachbilltimes(0);
		workBill.setOrder(order);
		
		//将工单集合放入订单对象
		//创建工单集合并将工单放入该集合
		Set<WorkBill> set=new HashSet<>();
		set.add(workBill);
		//将工单集合赋值为order的属性
		order.setWorkBills(set);
		//保存订单对象和工单对象
		workBillService.save(workBill);
		orderDao.save(order);
		
	}

}
