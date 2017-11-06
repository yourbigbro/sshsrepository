package web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import service.ProductService;


public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ShowServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ProductService pro = new ProductService();
		List<Product> products;
		try {
			products= pro.showAllProduct();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		request.setAttribute("products", products);//将product当做属性传递给request
		request.getRequestDispatcher("/product_list.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
