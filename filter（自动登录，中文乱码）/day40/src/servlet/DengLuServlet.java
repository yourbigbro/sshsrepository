package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import domain.YongHu;
import service.DengLuService;

@WebServlet(name="DengLuServlet",urlPatterns="/DengLuServlet")
public class DengLuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DengLuServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");*/
		
		DengLuService dse=new DengLuService();
		boolean boo = false;
		try {
			boo = dse.dengLu(request.getParameter("username"),request.getParameter("password"));
		} catch (SQLException e) {
			System.out.println("��¼�����쳣");
			e.printStackTrace();
		}
		if(boo==false){
			response.getWriter().write("�˺Ż�������󣬽����������ת����¼ҳ��");
			/*try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("�߳�˯���쳣");
				e.printStackTrace();
			}
			response.sendRedirect("/day40/login.jsp");//�ض���Ļ�Ҫд��Ŀ������Ϊ�ǿͻ���·��
*/			/*request.getRequestDispatcher("/login.jsp").forward(request, response); ���Ҳ��*/
		}else {
			/*response.getWriter().write("��½�ɹ�");*/
			YongHu yongHu=new YongHu();
			yongHu.setUsername(request.getParameter("username"));
			yongHu.setPassword(request.getParameter("password"));
			if(request.getSession().getAttribute("YongHu")==null){//filter��û����session�Ļ��Ż���servlet������session
				request.getSession().setAttribute("YongHu",yongHu);//������session������session
				System.out.println(request.getParameter("autoLogin"));
				System.out.println("������servlet�������õ�session");
			}
			
			if(request.getParameter("autoLogin") != null){//�����ѡ���Զ���¼�Ļ�
				System.out.println("�Ǻ�");
				String string=request.getParameter("username")+":"+request.getParameter("password");
				String encodestr=URLEncoder.encode(string, "utf-8");//������cookie���б��룬����ᱨ�쳣��java.lang.IllegalArgumentException: Control character in cookie value or attribute
				Cookie cookie=new Cookie("acookie", encodestr);
				cookie.setMaxAge(60*60*24*7);//ʱ������ñ��������cookie֮ǰ
				response.addCookie(cookie);//���cookie
				
				/*response.getWriter().write("�����cookie");*/
				System.out.println("�����cookie");
			}
			response.getWriter().write("��½�ɹ�");
			/*request.getRequestDispatcher("/encoding.html");//ת����½�ɹ�ҳ��
*/		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);//doGet����Ļ�����Ҳ����ű���
	}

}