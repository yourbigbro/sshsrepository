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

//删除单个商品
public class DeleteOneProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public DeleteOneProductServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddProductService aps = new AddProductService();
		String pid = request.getParameter("pid");
		aps.deletePid(pid);
		//更新页面
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
