package web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Product;
import service.AddProductService;
import service.ProductService;

public class ModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ModifyServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Product pro=new Product();
		try {
			BeanUtils.populate(pro, request.getParameterMap());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//将信息封装到pro对象中
		AddProductService aps = new AddProductService();
		aps.change(pro);//调用service层
		//将更新的信息重新显示到页面
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
