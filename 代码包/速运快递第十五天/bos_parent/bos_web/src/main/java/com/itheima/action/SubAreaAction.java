package com.itheima.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.itheima.domain.Area;
import com.itheima.domain.SubArea;
import com.itheima.service.AreaService;
import com.itheima.service.FixedAreaService;
import com.itheima.service.SubAreaService;
import com.itheima.utils.FileUtils;
import com.itheima.utils.action.common.CommonAction;

//注意，各个类都可以继承一个抽取出来的公共父类。正因为大家都用它他才会被抽取出来，才会被称为公共父类
public class SubAreaAction extends CommonAction<SubArea>{
	
	@Autowired
	private SubAreaService subAreaService;
	
	@Action("listAllSubArea")
	public String listAllSubArea(){
		//查找到所有的SubArea对象
		List<SubArea> list=subAreaService.findAll();
		//封装前台页面传来的数据为Pageable对象
		//方法的前面加不加this都行
		this.setPageable();
		//利用Pageable对象查询数据库并返回带有分页信息的查询结果
		//Page<Courier> page=courierService.findAll(pageable);
		//改造原来的findAll方法，将前面得到的spe对象当作参数传进去
		//注意，pageable是用于jpa查询分页信息，Page<SubArea> pageData则是查询的结果
		Page<SubArea> pageData=subAreaService.findAll(pageable);
		//不仅当前实体类中的集合属性可以排除在转化成json之外，作为实体类集合的属性的集合中的集合也可以排除在外，也就是排除孙子集合
		java2json(pageData,new String[]{"fixedArea","subareas"});
		//别忘了return字符串
		return NONE;
	}
	
	//该方法用于添加新的分区信息。返回值用于回到以前的页面
	@Action(value="subareaAction_save",results={@Result(name="success",type="redirect",location="/pages/base/sub_area.html")})
	public String subareaActionSave(){
		//利用封装前台页面表单数据的model对象
		subAreaService.save(model);
		return SUCCESS;
	}
	
	//该方法用于查询没有定区的分区(也就是定区属性值为null的分区)
	@Action("findSubAreaWithFixedAreaIdIsNull")
	public String findSubAreaWithFixedAreaIdIsNull(){
		List<SubArea> list=subAreaService.findByFixedAreaIsNull();
		this.java2json(list, new String[]{"area"});
		return NONE;
	}
	
	//属性驱动接收前台页面传来的定区编号id
	private String idss;
	
	public void setIdss(String idss) {
		this.idss = idss;
	}
	
	//引入它是为了在findSubAreaWithFixedAreaIdIsSomeone方法中查询定区id:idss所对应的定区对象并与分区解除关联
	@Autowired
	private FixedAreaService fixedAreaService;
	
	//该方法用来查询定区属性值为某一特定值的分区并将这些分区对象的定区属性(定区属性是对象)变为null(也就是解除关联)
	@Action("findSubAreaWithFixedAreaIdIsSomeone")
	public String findSubAreaWithFixedAreaIdIsSomeone(){
		
		//根据分区id找出符合条件的分区，并放在右边的选择框内(从数据库中查询也是事物的一部分)
		List<SubArea> list=subAreaService.findByFixedAreaIsSomeone(idss);
		//注意，信息返回前台页面之后仍旧可以接着执行该方法中没执行完的代码
		//懒加载的报错是逐个的，改一个才能出现下一个报错。假如不知道该排除哪个属性，可以依据报错逐渐的排除属性，但是比较麻烦。
		//this.java2json(list, new String[]{"area","couriers","subareas"});
		//上面的couriers和subareas都属于fixedArea，而fixedArea我们此处是用不到的，所以将fixedArea直接排除即可。
		//所以分析需求很重要，不用的集合不管是否涉及到懒加载直接排除，省去了点进去看是否有需要排除的属性的功夫。
		this.java2json(list, new String[]{"area","fixedArea"});
		
		//为了防止查询也是同一个事务，解决方法是再查询list一次。java2json无法放在service层中
		//解除定区和分区的关联
		subAreaService.removeRelationship(idss);
		
		return NONE;
	}
	
	
	//该方法用于将分区信息以excel文件的形式让用户进行下载
	@Action("downloadSubArea")
	public String downloadSubArea() throws IOException{
		
		//首先查找到集合形式的所有分区信息
		List<SubArea> subAreas = subAreaService.findAll();
		//创建一个xls文件
				HSSFWorkbook hssf = new HSSFWorkbook();
				//创建标签页
				HSSFSheet sheet = hssf.createSheet("分区数据");
				//创建标题行
				HSSFRow titleRow = sheet.createRow(0);
				//设置表头信息
				titleRow.createCell(0).setCellValue("分区编号");
				titleRow.createCell(1).setCellValue("所在省份");
				titleRow.createCell(2).setCellValue("所在城市");
				titleRow.createCell(3).setCellValue("所在区域");
				//设置数据区域
				for (SubArea subArea : subAreas) {
					HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
					dataRow.createCell(0).setCellValue(subArea.getId());
					//注意，当分区里面没写对应的区域时就会报空指针异常java.lang.NullPointerException，而不是因为区域里面没有省市区的信息。
					dataRow.createCell(1).setCellValue(subArea.getArea().getProvince());
					dataRow.createCell(2).setCellValue(subArea.getArea().getCity());
					dataRow.createCell(3).setCellValue(subArea.getArea().getDistrict());
				}
				//下载文件【一个流两个头】
				//创建一个输出流
				ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
				//设置文件的类型【告诉浏览器文件的类型】
				ServletActionContext.getResponse().setContentType("application/vnd.ms-excel");
				//文件名
				String filename = "分区数据.xls";
				//浏览器的类型和版本
				String agent = ServletActionContext.getRequest().getHeader("User-Agent");
				//在不同浏览器下显示中文
				filename = FileUtils.encodeDownloadFilename(filename, agent);
				//设置文件打开方式以及文件名content-disposition", "attachment以附件形式打开
				ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename );
				//把文件写出到浏览器
				hssf.write(out);
		return NONE;
	}
	
	//统计各省的分区总数量
	@Action("listPie")
	public String listPie(){
		
		List<Object[]>list=subAreaService.listPie();
		this.java2json(list, null);
		return NONE;
	}
}
