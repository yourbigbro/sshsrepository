package com.itheima.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.core.helpers.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.exception.MyException;
import com.itheima.pojo.Items;
import com.itheima.pojo.QueryVo;
import com.itheima.service.ItemsService;

@Controller
public class ItemsController {

	@Autowired
	private ItemsService itemsService;
	
	//该方法用于查询所有的items对象的集合并展示在页面上
	@RequestMapping("itemList")
	public ModelAndView findAllItems() throws MyException{
		
		//必须放在if条件语句里面，否则下面的语句会报错
		/*if (true) {
			throw new MyException("自定义异常(预期异常)");
		}*/
		
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
	//该方法也负责文件上传
	@RequestMapping("updateitem")
	public ModelAndView updateItemById(Items items,MultipartFile pictureFile) throws MyException, IllegalStateException, IOException{
		
		//获得上传图片的文件全名
		String filename = pictureFile.getOriginalFilename();
		//取得文件名的后缀(这里是jpg)
		String suffix = filename.substring(filename.lastIndexOf("."));
		//随机生成后缀之前的名字
		String uuid = UUID.randomUUID().toString();
		//拼接成完整的名字
		String finalName=uuid+suffix;
		//将文件名保存到数据库
		items.setPic(finalName);
		//上传图片到文件夹(要想用单斜杠就要用向左边倾斜的那个斜杠)
		//注意，upload后面必须加斜杠，否则会将文件夹名和文件名弄混淆
		pictureFile.transferTo(new File("d:/upload/"+finalName));
		
		//将用户修改后的信息保存到数据库
		itemsService.updateItemsById(items);
		//重新查询修改后的数据库并展示到页面
		ModelAndView view = this.findAllItems();
		return view;
				
	}
	
	//该方法用于批量删除和批量修改(该方法是为了演示Controller可以从前台接收数组(ids)作为参数)
	@RequestMapping("deleteMany")
	public ModelAndView deleteMany(String[] ids,QueryVo queryVo) throws MyException{
		
		List<Items> list = queryVo.getItemList();
		//批量修改
		itemsService.updateItems(list);
		//删除选中项。假如没有要删除的项就不进行操作了
		if(ids!=null&&ids.length!=0){
			//批量删除
			itemsService.deleteItemsById(ids);
			//删除之后重新将数据库的内容展示在前台页面上
			
		}
		
		ModelAndView modelAndView = this.findAllItems();
		return modelAndView;
	}
	
	//该方法用于测试json格式的数据和java对象之间的相互转换
	//后面的@RequestBody用于将json转换成items对象，前面的反之
	@RequestMapping(value="testJsonToJava",method=RequestMethod.POST)
	public @ResponseBody Items testJsonToJava(@RequestBody Items items){
		
		return items;
	}
	
	@RequestMapping("restfulTest/{id}")
	public @ResponseBody Items restfulTest(@PathVariable() Integer id){
		
		Items items = itemsService.findItemsById(id);
		return items;
	}
}
