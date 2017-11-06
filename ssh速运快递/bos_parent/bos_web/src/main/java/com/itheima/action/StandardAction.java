package com.itheima.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.itheima.domain.Standard;
import com.itheima.service.StandardService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//注意，该类所在的包的包名必须以action,actions,struts,struts2结尾
//注意是ModelDriven而不是DrivenModel
@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("struts-default")
public class StandardAction extends ActionSupport implements ModelDriven<Standard> {
	
	//创建Standard对象用于接收前台数据
	private Standard standard=new Standard();
	
	@Override
	public Standard getModel() {
		return standard;
	}
	
	@Autowired(required=false)
	private StandardService standardService;
	
	//用于测试
	//注意，不写前面的List<Standard> list=是无法点进去创建方法的
	//注意，表中没有数据时该方法会报错
	//List<Standard> list=standardService.findAll();
	
	//定义方法并添加注解(保存数据并回显)(注意相对路径是什么)
	//保存数据之后不用跳转到查询方法就直接回到页面的原因是页面会向后台发请求并查询信息
	@Action(value="save_standard",results={@Result(name="success",type="redirect",location="/pages/base/standard.html")})
	public String save(){
		standardService.save(standard);
		return SUCCESS;
	}
	
	//用分页的方式查询Standard表中的所有信息
	//首先要接收前台传来的page(当前页码)和rows(每页显示多少条数据)两个属性
	//注意page和rows都是int类型
	private int page;
	private int rows;
	
	//注意封装属性只要set方法就行
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	//这里不用写返回到哪个页面，因为框架已经帮我们做了
	//分页查询每一页的数据(注意是没有返回值的，因为框架会自动返回两个参数page和，)
	@Action(value="find_standard")
	public String findAllByPagination() throws IOException{
		//将前台页面传来的页码和页的大小的信息封装进PageAble对象
		Pageable pb = new PageRequest(page-1, rows);
		//用对象进行数据库的查询。数据库依然用findAll方法，只不过是findAll方法由无参变成了有参
		Page<Standard> pageData=standardService.queryAllByPagenation(pb);
		//将数据库查询得到的对象封装进map集合
		//第二个参数是object是因为json的键值对的值的数据类型无法确定，所以用object统称
		Map<String, Object> map=new HashMap<String, Object>();
		//注意返回的是long类型的数据
		//total指的是表中的记录的总数量，rows指的是当前页的对象集合
		Long total=pageData.getTotalElements();
		List<Standard> rows = pageData.getContent();
		map.put("total", total);
		map.put("rows", rows);
		//将map集合转化为json的形式
		//注意有好几个包中都有JSONObject类，记得选择import net.sf.json.JSONObject包
		JSONObject json = JSONObject.fromObject(map);
		//将json变成字符串，在设置了编码格式之后传回前台页面
		String jsonString=json.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		//注意，write()方法也可以换成print()和println()方法
		//这里会抛出异常，但是我们将它抛出去的原因是我们已经确认他不会出错。否则的话web层的异常是不应该继续往上抛的
		ServletActionContext.getResponse().getWriter().write(jsonString);
		//注意这里是return none而不是return null。只要是继承ActionSupport就返回NONE
		return NONE;
	}
	
	//这里不用写返回到哪个页面，因为框架已经帮我们做了
	@Action(value="standard_findAll")
	public String findAll() throws IOException{
		List<Standard> list=standardService.findAll();
		//注意，处理list集合和处理map集合所用的对象是不同的，前者是JSONArray，后者是JSONObject
		JSONArray json = JSONArray.fromObject(list);
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json.toString());
		return NONE;
	}
}
