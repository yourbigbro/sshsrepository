package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.Product;
import service.AddProductService;
import service.ProductService;


public class DeleteManyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteManyProductServlet() {
        super();
        }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");//�÷���ֻ��post����ʽ������
		response.setContentType("text/html;charset=utf-8");
		String[] pv = request.getParameterValues("delete");
		int i=0;
		for (String string : pv) {
			pv[i]=new String(string.getBytes("iso-8859-1"),"utf-8");//������string����pv[i]
			i++;
		}
		AddProductService aps = new AddProductService();
		//ִ��ɾ������
		aps.delete(pv);
		//����ҳ��
		ProductService ps = new ProductService();
		List<Product> sap;
		try {
			sap = ps.showAllProduct();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		request.setAttribute("products", sap);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
