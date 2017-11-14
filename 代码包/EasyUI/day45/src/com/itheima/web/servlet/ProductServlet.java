package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.itheima.domain.Product;
import com.itheima.domain.pageBean;
import com.itheima.service.ProductService;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ProductServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//ÿһҳ������
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		//��ǰҳ��
		int pageNumber=Integer.parseInt(request.getParameter("page"));
		pageBean<Product> pageBean = new pageBean<Product>();
		//����ÿһҳ��������ҳ�洫���Ĳ�����
		pageBean.setPageSize(pageSize);
		//���õ�ǰҳ�루ҳ�洫���Ĳ�����
		pageBean.setPageNumber(pageNumber);
		//�����ܼ�¼��
		ProductService ps = new ProductService();
		pageBean=ps.setTotal(pageBean);
		//���õ�ҳ�Ķ��󼯺�
		pageBean=ps.setRows(pageBean);
		Gson gson = new Gson();
		String json = gson.toJson(pageBean);
		//�����ݴ��ص�ǰ̨ҳ��
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
