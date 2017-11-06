package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import net.sf.json.JSONArray;
import service.AjaxService;
//记得用response.getWriter().write()在响应体内传递信息
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");//注意与以往不同!!!!!!!!!!!!!!!!!!!!!!!!!!
		String value = request.getParameter("productname");//获取传来的值
		AjaxService as = new AjaxService();
		List<User> user=as.checkUsername(value);//在数据库中查询信息
		//将查询到的字符串变成json对象
		if(user==null){
			response.getWriter().write("");
		}else {
			response.getWriter().write(JSONArray.fromObject(user).toString());//写入json，集合里面包含至多五个对象
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
