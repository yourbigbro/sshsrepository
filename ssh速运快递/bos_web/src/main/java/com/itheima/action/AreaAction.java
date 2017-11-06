package com.itheima.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.itheima.domain.Area;
import com.itheima.service.AreaService;
import com.itheima.utils.FileUtils;
import com.itheima.utils.PinYin4jUtils;
import com.itheima.utils.action.common.CommonAction;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;




@Controller
//下面的注解属于struts2，它是将单线程变为多线程
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/")
public class AreaAction extends CommonAction<Area> {
	
	@Autowired
	private AreaService areaService;
	
	//用属性驱动的方式获得前台传来的name属性值xlsFile，它就是文件对象，而不只是一个变量
	private File xlsFile;
	//set方法为声明的变量赋予前台传来的的值
	public void setXlsFile(File xlsFile) {
		//this.xlsFile指的是私有变量，后面的xlsFile指的是前台页面传来的同名属性值
		this.xlsFile = xlsFile;
	}

	//该方法用于将用户选定的规定格式的excel文件上传到数据库
	@Action("upload_xlFile")
	public String uploadXls() throws IOException{
		
		String flag="1";
		//检测是否成功将excel表格的内容保存到了数据库
		try {
			//将文件对象封装到输入流
			FileInputStream file = new FileInputStream(xlsFile);
			//创建HSSFWorkbook对象
			HSSFWorkbook hssf = new HSSFWorkbook(file);
			//获取excel表中的第一页
			HSSFSheet sheet = hssf.getSheetAt(0);
			//创建集合用于盛放封装好的包含每一行信息的Area对象以备保存到数据库
			//放入集合中可以集中保存到数据库而不是每读一行都保存一次，减小了数据库的压力
			ArrayList<Area> arrayList = new ArrayList<>();
			
			//循环遍历获得第一页中的除第一行中的每一行
			//它可以自动补出row，说明sheet中包含的就是row，就是这个语法规则
			for (Row row : sheet) {
				//不遍历第一行
				if(row.getRowNum()!=0){
					//将这一行的数据用构造方法的方式封装到Area对象中(需要事先在Area实体类中创建相应的非全属性构造方法。还要注意构造方法的参数顺序)
					//注意，在用getCell获得列之后还要用getStringCellValue获得其中的值，注意用getCell不会直接获得值
					Area area = new Area(row.getCell(0).getStringCellValue(),//编号
										row.getCell(1).getStringCellValue(),//省份
										row.getCell(2).getStringCellValue(),//城市
										row.getCell(3).getStringCellValue(),//区域
										row.getCell(4).getStringCellValue()//邮编
										);
					
					//将城市编码(也就是城市名的拼音)和简码计算出来并加入Area对象
					//得到城市编码
					String citycode=row.getCell(2).getStringCellValue();
					//将"市"字去掉
					citycode = citycode.substring(0, citycode.length()-1);
					//得到最终城市编码(第二个参数是分隔符，不写的话默认是空格，不符合要求)
					citycode=PinYin4jUtils.hanziToPinyin(citycode, "");
					area.setCitycode(citycode);
					//得到简码(包含省市区的缩写)
					String shortcode=row.getCell(1).getStringCellValue().substring(0, row.getCell(1).getStringCellValue().length()-1)
									+row.getCell(2).getStringCellValue().substring(0, row.getCell(2).getStringCellValue().length()-1)
									+row.getCell(3).getStringCellValue().substring(0, row.getCell(3).getStringCellValue().length()-1);
					//得到的是首字母数组
					String[] headByString = PinYin4jUtils.getHeadByString(shortcode);
					//用StringUtils工具类将数组转换成字符串
					shortcode=StringUtils.join(headByString);
					//将首字母数组变成字符串
					area.setShortcode(shortcode);
					//将Area对象追加到集合
					arrayList.add(area);
				}
			}
			//将集合中的内容保存到数据库
			if(arrayList.size()>0){
				//保存数据自然是没有返回值的
				areaService.saveXls(arrayList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//flag为零的话表示存入数据库的操作失败了
			flag="0";
		}
		//通过检测flag向前端页面传回是否操作成功的信息
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(flag);
		
		return NONE;
	}
	
	//页面的地址是不能直接拖到后台中的，只能拖到html页面中
	//该方法的作用是从数据库中找出所有的Area对象并且转换成字符串传回到前台页面进行显示
	@Action("areaAction_listajax")
	public String listajax(){
		
		//从数据库中查询name属性(通过实体类Area)
		List<Area> list=areaService.listName();
		
		//该方法不仅将list集合转成了字符串，还将转换后的信息回传到了前台页面
		//排除subareas属性是因为它里面还包含集合。也就是集合里面不能再嵌套集合，否则会因为懒加载而报错。但是subareas里面的成员变量不包含集合的话就可以不排除他
		this.java2json(list, new String[]{"subareas"});
		return NONE;
	}
	
	//配置连接池(注意注解不能忘，否则下面用的时候会报空指针异常java.lang.NullPointerException)
	@Autowired
	private DataSource dataSource;
	
	@Action("exportPdf")
	public String exportPdf() throws Exception{
		
		// 读取 jrxml 文件(当文件相同时，每次读取的都是同一类信息，只是数据是实时的)
		String jrxml = ServletActionContext.getServletContext().getRealPath("./jr/report1.jrxml");
		// 准备需要数据
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("company", "黑马");
		// 准备需要数据
		JasperReport report = JasperCompileManager.compileReport(jrxml);
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource.getConnection());

		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream ouputStream = response.getOutputStream();
		// 设置相应参数，以附件形式保存PDF
		response.setContentType("application/pdf");
		response.setCharacterEncoding("UTF-8");
		//设置pdf文件的名字
		response.setHeader("Content-Disposition", "attachment; filename=" + FileUtils.encodeDownloadFilename("工作单.pdf",
				ServletActionContext.getRequest().getHeader("user-agent")));
		// 使用JRPdfExproter导出器导出pdf
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		exporter.exportReport();// 导出
		ouputStream.close();// 关闭流
		//依然返回到原来的页面
		return NONE;
	}

}
