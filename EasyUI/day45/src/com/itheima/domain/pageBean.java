package com.itheima.domain;

import java.util.List;

public class pageBean<T> {//���͡������ʱ���÷��ͣ�ʵ������ʱ�����÷��ͣ�����Product�������
	//�ܼ�¼��
	private int total;//���̶ֹ�������ҳ���޷�����json
	//ҳ���С
	private int pageSize;
	//��ǰ��ʾ��ҳ��
	private int pageNumber;
	//��ǰҳ�Ķ��󼯺�
	private List<T> rows;//���̶ֹ�������ҳ���޷�����json
	//��ҳ��
	private int totalPage;
	//��ǰҳ����ʼ����
	private int startIndex;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<T> getRows() {//����
		return rows;
	}
	public void setRows(List<T> rows) {//����
		this.rows = rows;
	}
	//���ô������Ĳ�������pagebean�ڲ��������ü��ɣ���ֻ��������Ա�����Ϳ��Եõ���
	public int getTotalPage() {
		return this.getTotal()%this.getPageSize()==0?(this.getTotal()/this.getPageSize()):(this.getTotal()/this.getPageSize()+1);
	}
	//�����set��������д����Ϊû��
	/*public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}*/
	//���ô������Ĳ�������pagebean�ڲ��������ü��ɣ���ֻ��������Ա�����Ϳ��Եõ���
	public int getStartIndex() {//�����þ��У���Ϊ���������õ����ֵ�����ļ���ʵ������д����dao���档
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public pageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public pageBean(int total, int pageSize, int pageNumber, List<T> rows, int totalPage, int startIndex) {
		super();
		this.total = total;
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.rows = rows;
		this.totalPage = totalPage;
		this.startIndex = startIndex;
	}
	
}
