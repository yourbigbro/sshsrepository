package domain;
//��ҳ����������
//����û��string���͵�����

import java.sql.SQLException;
import java.util.List;
import service.AddProductService;
import service.ProductService;

public class PageBean{
	//��ǰҳ�루ǰ̨�û����ݣ�
	private int pageNumber;
	//��ǰҳ��ʼ����
	private int startIndex;
	//��ҳ��
	private int totalPage;
	//��¼���ܸ������ⲿ���룩
	private int totalRecord;
	//ÿҳ��¼�������ⲿ���룩
	private int pageSize;
	//��ǰҳ����Ʒ���󼯺ϣ��ⲿ���룩 
	private List<Product> data;
	
	public int getPageNumber() {
		return pageNumber;//�û�����
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
		return pageSize;//д��
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Product> getData() {//���Ǳ�ҳ�����ݶ��������е�����
		return new AddProductService().getList(this.getStartIndex(),this.getPageSize());
	}
	public void setData(List<Product> data) {
		this.data = data;
	}
	public PageBean() {
		super();
	}
	
	
}

