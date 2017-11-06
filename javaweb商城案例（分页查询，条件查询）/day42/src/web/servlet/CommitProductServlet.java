package web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Category;
import domain.Product;
import service.AddProductService;
import service.ProductService;
import utils.UUIDUtils;


public class CommitProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CommitProductServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Product product = new Product();
		try {
			BeanUtils.populate(product, request.getParameterMap());//����Ϣ��װ��������
			product.setPdate(new Date());//ʱ��ĸ�ʽ��������������������
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		product.setPflag("0");//Ĭ��Ϊ�ϼ�״̬
		//����category
		Category category=new Category();
		category.setCid(request.getParameter("cid"));
		category.setCname(request.getParameter("pname"));
		//ʹ�ù����category
		product.setCategory(category);
		product.setPid(UUIDUtils.getUUID());
		product.setMarket_price("1235");
		//����Ϣ���浽���ݿ���
		AddProductService  aps=new AddProductService();
		try {
			aps.addProduct(product);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		//�������֮��ȡ����������ʾ��ҳ����
		ProductService ps = new ProductService();
		List<Product> sp;
		try {
			 sp = ps.showAllProduct();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		request.setAttribute("products", sp);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
