package web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.PageBean;
import domain.Product;
//分页操作。最开始的时候访问这个页面，然后到jsp页面。当点击分页的按钮时，仍然返回这个servlet，否则不来这个servlet。
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PageBean pageBean = new PageBean();
		int pageNumber ;
		//设置当前页码
		if(request.getParameter("pageNumber")==null){
			pageNumber=1;
		}else {
			pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		}
		//设置当前页码
		pageBean.setPageNumber(pageNumber);
		//设置每页的大小
		pageBean.setPageSize(8);
		System.out.println(pageBean.getStartIndex());//没问题
		System.out.println(pageBean.getData().size());//没问题
		System.out.println(pageBean.getTotalPage());//没问题
		System.out.println(pageBean.getTotalRecord());//没问题
    	List<Product> products = pageBean.getData();
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("products", products);
		request.setAttribute("pageNumber", pageNumber);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
