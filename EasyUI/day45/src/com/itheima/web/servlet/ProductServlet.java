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
		//每一页的行数
		int pageSize=Integer.parseInt(request.getParameter("rows"));
		//当前页码
		int pageNumber=Integer.parseInt(request.getParameter("page"));
		pageBean<Product> pageBean = new pageBean<Product>();
		//设置每一页的行数（页面传来的参数）
		pageBean.setPageSize(pageSize);
		//设置当前页码（页面传来的参数）
		pageBean.setPageNumber(pageNumber);
		//设置总记录数
		ProductService ps = new ProductService();
		pageBean=ps.setTotal(pageBean);
		//设置当页的对象集合
		pageBean=ps.setRows(pageBean);
		Gson gson = new Gson();
		String json = gson.toJson(pageBean);
		//将数据传回到前台页面
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
