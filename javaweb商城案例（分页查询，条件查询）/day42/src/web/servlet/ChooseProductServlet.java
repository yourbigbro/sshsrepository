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
//对商品进行筛选显示
public class ChooseProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChooseProductServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取jsp页面传来的信息
		String describe = request.getParameter("describe");
		String selectcname = request.getParameter("selectcname");
		//查询数据库
		AddProductService aps = new AddProductService();
		List<Product> products = aps.selectProduct(describe, selectcname);
		List<Category> categorys = aps.getCategory();
		request.setAttribute("products", products);
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
