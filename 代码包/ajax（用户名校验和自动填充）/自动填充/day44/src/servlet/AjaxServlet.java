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
//�ǵ���response.getWriter().write()����Ӧ���ڴ�����Ϣ
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");//ע����������ͬ!!!!!!!!!!!!!!!!!!!!!!!!!!
		String value = request.getParameter("productname");//��ȡ������ֵ
		AjaxService as = new AjaxService();
		List<User> user=as.checkUsername(value);//�����ݿ��в�ѯ��Ϣ
		//����ѯ�����ַ������json����
		if(user==null){
			response.getWriter().write("");
		}else {
			response.getWriter().write(JSONArray.fromObject(user).toString());//д��json������������������������
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
