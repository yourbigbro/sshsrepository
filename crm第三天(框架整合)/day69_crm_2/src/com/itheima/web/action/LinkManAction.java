package com.itheima.web.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.domain.LinkMan;
import com.itheima.service.LinkManService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//该项目未涉及到分页

@Controller("linkManAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	
	//封装前台页面传来的数据(模型驱动)
	private LinkMan linkMan= new LinkMan();
	
	@Override
	public LinkMan getModel() {
		return linkMan;
	}
	
	@Autowired
	private LinkManService linkManService;
	
	private List<LinkMan> linkManss;

	private LinkMan linkMan2;
	
	public LinkMan getLinkMan2() {
		return linkMan2;
	}

	public void setLinkMan2(LinkMan linkMan2) {
		this.linkMan2 = linkMan2;
	}

	//设置get/set方法使得前台页面可以取得数据库的查询出来的信息，也就是联系人的集合
	public List<LinkMan> getLinkManss() {
		return linkManss;
	}

	public void setLinkManss(List<LinkMan> linkManss) {
		this.linkManss = linkManss;
	}
	
	//点击添加按钮经过该方法跳转到add.jsp页面
	@Action(value="addLinkMan",results={@Result(name="list",location="/jsp/linkman/add.jsp")})
	public String toAdd(){
		return "list";
	}

	//add.jsp添加完之后再展示出来(也就是再跳转到list页面)
	@Action(value="toAddLinkMan",results={@Result(name="list",type="redirectAction",location="listLinkMan.action")})
	public String add(){
		linkManService.add(linkMan);
		return "list";
	}
	
	//将数据库之中的所有内容展示到前台页面
	@Action(value="listLinkMan",results={@Result(name="list",location="/jsp/linkman/list.jsp")})
	public String list(){
		
		//当没有为criteria(即离线对象)赋任何值的时候就会查询数据库中的所有信息
		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		linkManss=linkManService.list(criteria);
		return "list";
	}
	
	//修改某一条联系人信息(将信息回显到edit.jsp页面)
	@Action(value="changeLinkMan",results={@Result(name="edit",location="/jsp/linkman/edit.jsp")})
	public String change(){
		
		//从linkMan模型驱动对象的id属性得到前台页面传来的LinkMan对象的id并封装到criteria对象并从数据库的联系人表中查出对应的linkman3对象并回显到页面
		DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
		//注意，integer要用""来转换成字符串，而long不用转换就能应用在isNotBlank方法中
		System.out.println(linkMan.getLkmId());
		if(linkMan!=null&&StringUtils.isNotBlank(linkMan.getLkmId()+"")){
			//给criteria对象赋值
			criteria.add(Restrictions.eq("lkmId", linkMan.getLkmId()));
			//该linkMan2变量是为了将信息传递到前台页面去的，而前面的linkMan变量是用来封装从前台页面传来的对象的，他们不仅用途不同，而且声明方法也不同，
			//linkMan是模型驱动用getModel方法而不用get/set方法，linkMan2是用get和set方法，他们两个的相同点是都会声明私有的成员变量。
			linkMan2=linkManService.findByCriteria(criteria);
		}
		//linkMan2=linkManService.fuckFind(linkMan);
		//跳转到edit.jsp页面
		return "edit";
	}
	
	//修改某一条联系人信息(将信息回显到edit.jsp页面)
	@Action(value="editLinkMan",results={@Result(name="list",type="redirectAction",location="listLinkMan.action")})
	public String edit(){
		//得到用对象模型封装好的LinkMan对象并用它进行数据库修改。由于是修改，所以没有返回值
		linkManService.edit(linkMan);
		//跳转到查询数据库中所有信息的页面
		return "list";
	}
	
	//删除数据库中的某一条信息并重新查询所有联系人信息并显示在list.jsp页面上
	@Action(value="deleteLinkMan",results={@Result(name="list",type="redirectAction",location="listLinkMan.action")})
	public String delete(){
		
		//得到用对象模型封装好的LinkMan对象并用它进行数据库记录的。由于是删除，所以没有返回值
		linkManService.delete(linkMan);
		//跳转到查询数据库中所有信息的页面
		return "list";
	}
}
