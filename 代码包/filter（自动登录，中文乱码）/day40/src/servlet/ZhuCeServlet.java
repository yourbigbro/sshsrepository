package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ZhuCeService;

@WebServlet(name="ZhuCeServlet",urlPatterns="/ZhuCeServlet")
public class ZhuCeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ZhuCeServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");*/
		
		ZhuCeService zce = new ZhuCeService();
		boolean boo=zce.zhuCe(request.getParameter("username"),request.getParameter("password"));
		if(boo){
			response.getWriter().write("�û����ظ�������֮��ص�ע��ҳ��");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("�߳�˯���쳣");
				e.printStackTrace();
			}
			request.getRequestDispatcher("index.jsp");
		}else {
			response.getWriter().write("ע��ɹ�");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
