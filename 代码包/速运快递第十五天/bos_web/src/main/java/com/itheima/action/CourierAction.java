package com.itheima.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.domain.Courier;
import com.itheima.domain.Standard;
import com.itheima.service.CourierService;
import com.itheima.utils.action.common.CommonAction;


//注意模型驱动是接口而不是父类
public class CourierAction extends CommonAction<Courier> {
	
	//引用CourierService接口
	//注意，CourierService无法被抽取到公共类
	@Autowired
	private CourierService courierService;
	
	//该方法用于多条件筛选，并且分页显示数据
	@Action("list_courier")
	public String listCourier() throws IOException{
		
		//将courier对象中封装的四个条件筛选的值取出来(courier为全局变量)
		//获取快递员员工号
		//model变成蓝色估计是因为他是父类的成员变量，而不是在当前类中定义的
		final String courierNum = model.getCourierNum();
		//获取快递员公司(也就是所属单位)
		final String company = model.getCompany();
		//获取快递员类型
		final String type = model.getType();
		//获取取派标准(注意这是个对象而不是字符串)。获取这个对象的目的是获取其中的name属性。实际上name属性会在下面进行获得
		final Standard standard = model.getStandard();
		
		//用接口在内部创建了类对象，这个类对象做为参数用在条件查询上，毕竟得到的对象中包含着前台页面传来的条件
		Specification<Courier> spe = new Specification<Courier>() {

			@Override
			//其实query这个参数没用到
			public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//注意左边的类型是list而不是array
				List<Predicate> listP = new ArrayList<>();
				//注意是isnotblank而不是isnotempty
				if(StringUtils.isNotBlank(courierNum)){
					Predicate p=cb.equal(root.get("courierNum").as(String.class), courierNum);
					listP.add(p);
				}
				if(StringUtils.isNotBlank(company)){
					Predicate p=cb.equal(root.get("company").as(String.class), company);
					listP.add(p);
				}
				if(StringUtils.isNotBlank(type)){
					Predicate p=cb.equal(root.get("type").as(String.class), type);
					listP.add(p);
				}
				if(standard!=null&&StringUtils.isNotBlank(standard.getName())){
					//通过root对象获得join对象，然后用join对象代替root对象的功能
					Join<Object, Object> join = root.join("standard");
					//注意，get()里面的字符串对应于实体类的成员变量名
					Predicate p=cb.like(join.get("name").as(String.class),"%"+standard.getName()+"%");
					listP.add(p);
				}
				if(listP.size()>0){
					//将得到的集合转换成(固定长度的)Predicate[]类型的数组。记得声明数组的大小
					Predicate[] arrP = new Predicate[listP.size()];
					//里面的参数表示盛放集合转换完的数组。不用返回值，而arrP已经被赋值了
					listP.toArray(arrP);
					//将Predicate[]转换成Predicate，不清楚怎么转的
					Predicate and = cb.and(arrP);
					return and;
				}else {
					//return null的话就是没有传入任何要求，也就是普通的全体查询，因此可以用于增删改数据库之后的页面展示
					return null;
				}
			}
		};
		
		//封装前台页面传来的数据为Pageable对象
		//方法的前面加不加this都行
		this.setPageable();
		//利用Pageable对象查询数据库并返回带有分页信息的查询结果
		//Page<Courier> page=courierService.findAll(pageable);
		//改造原来的findAll方法，将前面得到的spe对象当作参数传进去
		Page<Courier> pageData=courierService.findAll(spe,pageable);
		
		java2json(pageData,new String[]{"fixedAreas"});
		//别忘了return字符串
		return NONE;
	}
	
	//注意，当没有后面的results时前面直接写save_courier而不用写value，有了results就要写value属性名(也就是键)
	//保存信息可以提交表单而不是转化成json数据传到后台是因为保存信息只不过是修改数据库然后再普通地分页查询一遍数据库
	@Action(value="save_courier",results={@Result(name="success",type="redirect",location="/pages/base/courier.html")})
	//注意，保存是不需要返回值的
	public String saveCourier(){
		//将模型驱动封装的前台页面传来的值当作参数传进去
		courierService.saveCourier(model);
		return SUCCESS;
	}
	
	//用属性驱动的方式获得前天传来的参数ids
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	//该方法用于删除数据，删除完之后再回到前台显示删除之后的结果
	@Action(value="delete_courier",results={@Result(name="success",type="redirect",location="/pages/base/courier.html")})
	//注意，保存是不需要返回值的
	public String deleteCourier(){
		String[] id = ids.split(",");
		for (String string : id) {
			//此项目的删除就是将是否作废改成已作废，而不是将数据从数据库里面删除。因此是update而不是delete
			//将字符串形式的id恢复成int类型的id
			courierService.updateDeltag(Integer.valueOf(string));
		}
		//用于跳转页面，进行操作之后信息的展示
		return SUCCESS;
	}
	
	//该方法用于为下来菜单提供所有的快递员对象
	@Action("findAllCourier")
	public String findAllCourier(){
		List<Courier> list=courierService.findAll();
		this.java2json(list, new String[]{"fixedAreas"});
		return NONE;
	}
	
	
}
