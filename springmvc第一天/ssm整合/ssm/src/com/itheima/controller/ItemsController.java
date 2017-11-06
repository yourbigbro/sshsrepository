package com.itheima.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.pojo.Items;
import com.itheima.service.ItemsService;

@Controller
public class ItemsController {

	@Autowired
	private ItemsService itemsService;
	
	//该方法用于查询所有的items对象的集合并展示在页面上
	@RequestMapping("itemList")
	public ModelAndView findAllItems(){
		
		List<Items> list = itemsService.findAllItems();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemList", list);
		modelAndView.setViewName("itemList");
		return modelAndView;
				 
	}
	
	//该方法用于查询id为某值的items对象并展示在修改页面上
	@RequestMapping("itemEdit")
	public String findItemById(HttpServletRequest request,Model model){
		
		//从前台页面取出来的是string而不是int，所以要进行转换
		Items items = itemsService.findItemsById(Integer.parseInt(request.getParameter("id")));
		model.addAttribute("item", items);
		return "editItem";
	}
	
	//该方法用于更新数据库，也就是保存用户的修改项
	@RequestMapping("updateitem")
	public ModelAndView updateItemById(Items items){
		
		//将用户修改后的信息保存到数据库
		itemsService.updateItemsById(items);
		//重新查询修改后的数据库并展示到页面
		ModelAndView view = this.findAllItems();
		return view;
				
	}
}
