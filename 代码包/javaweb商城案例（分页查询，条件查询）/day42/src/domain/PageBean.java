package domain;
//分页操作工具类
//里面没有string类型的数据

import java.sql.SQLException;
import java.util.List;
import service.AddProductService;
import service.ProductService;

public class PageBean{
	//当前页码（前台用户传递）
	private int pageNumber;
	//当前页起始索引
	private int startIndex;
	//总页数
	private int totalPage;
	//记录的总个数（外部输入）
	private int totalRecord;
	//每页记录个数（外部输入）
	private int pageSize;
	//当前页的商品对象集合（外部输入） 
	private List<Product> data;
	
	public int getPageNumber() {
		return pageNumber;//用户传入
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getStartIndex() {
		return (this.getPageNumber()-1)*getPageSize();
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getTotalPage() {
		return this.getTotalRecord()%this.getPageSize()==0?(this.getTotalRecord()/this.getPageSize()):(this.getTotalRecord()/this.getPageSize()+1);
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		int allRecord = 0;
		try {
			allRecord= new ProductService().showAllProduct().size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;//写死
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Product> getData() {//这是本页的数据而不是所有的数据
		return new AddProductService().getList(this.getStartIndex(),this.getPageSize());
	}
	public void setData(List<Product> data) {
		this.data = data;
	}
	public PageBean() {
		super();
	}
	
	
}

