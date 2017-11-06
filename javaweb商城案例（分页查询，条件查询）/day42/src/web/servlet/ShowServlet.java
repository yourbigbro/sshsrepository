package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import domain.Product;
import service.AddProductService;
import service.ProductService;


public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ShowServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ProductService pro = new ProductService();//��ȡ�����
		AddProductService aps = new AddProductService();//��ȡ�����
		List<Product> products;
		List<Category> categorys;
		try {
			products= pro.showAllProduct();//���÷���
			categorys = aps.getCategory();//���÷���
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		request.setAttribute("products", products);//��product�������Դ��ݸ�request
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/product_list.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
