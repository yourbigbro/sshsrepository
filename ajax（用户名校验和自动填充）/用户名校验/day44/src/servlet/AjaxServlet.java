package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.AjaxService;
//�ǵ���response.getWriter().write()����Ӧ���ڴ�����Ϣ
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String value = request.getParameter("username");//��ȡ������ֵ
		AjaxService as = new AjaxService();
		User user=as.checkUsername(value);//�����ݿ��в�ѯ��Ϣ
		if(user==null){
			response.getWriter().write("yes");
		}else {
			response.getWriter().write("no");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
