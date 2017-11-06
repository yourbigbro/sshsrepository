package web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.PageBean;
import domain.Product;
//��ҳ�������ʼ��ʱ��������ҳ�棬Ȼ��jspҳ�档�������ҳ�İ�ťʱ����Ȼ�������servlet�����������servlet��
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ����ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PageBean pageBean = new PageBean();
		int pageNumber ;
		//���õ�ǰҳ��
		if(request.getParameter("pageNumber")==null){
			pageNumber=1;
		}else {
			pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		}
		//���õ�ǰҳ��
		pageBean.setPageNumber(pageNumber);
		//����ÿҳ�Ĵ�С
		pageBean.setPageSize(8);
		System.out.println(pageBean.getStartIndex());//û����
		System.out.println(pageBean.getData().size());//û����
		System.out.println(pageBean.getTotalPage());//û����
		System.out.println(pageBean.getTotalRecord());//û����
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
