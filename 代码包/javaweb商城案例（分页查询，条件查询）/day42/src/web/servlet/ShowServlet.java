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
		ProductService pro = new ProductService();//获取类对象
		AddProductService aps = new AddProductService();//获取类对象
		List<Product> products;
		List<Category> categorys;
		try {
			products= pro.showAllProduct();//调用方法
			categorys = aps.getCategory();//调用方法
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		request.setAttribute("products", products);//将product当做属性传递给request
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/product_list.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
