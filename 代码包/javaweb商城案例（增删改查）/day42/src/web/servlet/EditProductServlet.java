package web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import domain.Product;
import service.AddProductService;
//编辑商品
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EditProductServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		//根据pid的值从数据库中查询并返回一个product对象
		AddProductService aps = new AddProductService();
		Product product=aps.getProduct(pid);
		List<Category> categorys=aps.getCategory();//获得所有的category
		//设置成属性并转发给change_product.jsp页面
		request.setAttribute("product", product);
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/change_product.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
