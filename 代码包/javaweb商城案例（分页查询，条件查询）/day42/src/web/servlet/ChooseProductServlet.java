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
//����Ʒ����ɸѡ��ʾ
public class ChooseProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChooseProductServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡjspҳ�洫������Ϣ
		String describe = request.getParameter("describe");
		String selectcname = request.getParameter("selectcname");
		//��ѯ���ݿ�
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
