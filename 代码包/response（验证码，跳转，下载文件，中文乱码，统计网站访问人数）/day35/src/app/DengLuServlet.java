package app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class DengLuServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	Service ser=new Service();
	ServletContext servletcontext;
	@Override
	public void init(){
		servletcontext=this.getServletContext();
		 int count =0;
		servletcontext.setAttribute("count",count);
	}
	
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		if(req.getHeader("Referer")!="http://localhost:8080/day35/denglu.jsp"){//����������ֹ�������ҳ���������.java��̨�ļ�
			res.getWriter().write("fuck you");
		}
		
		String pa1 = req.getParameter("username");//���ǰ̨�����ı���Ϣ
		String pa2 = req.getParameter("password");
		String xinxi = null;
		res.setCharacterEncoding("utf-8");//��ֹ����ǰ̨��������Ϣ�������루�ʺţ�
		res.setContentType("text/html;charset=utf-8");
		try {
			xinxi = ser.chaXun(pa1,pa2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(xinxi=="��ѯʧ��"){
			res.getWriter().write("<h3 style='color:blue;'>�û��������������</h3>");//��ǰ̨������Ϣ
			res.setHeader("Refresh","5;URL=http://localhost:8080/day35/denglu.jsp");//ע��·��ֻ����Ŀ���ƺ�ҳ������
		}else{
			int count=(int) servletcontext.getAttribute("count");
			count++;
			servletcontext.setAttribute("count", count);
			res.getWriter().write("<h3 style='color:bule'>��½�ɹ������ǵ�"+count+"λ��½��</h3>");
			/*res.setHeader("Refresh","5;URL=http://localhost:8080/day35/xiazai.html");//����ˢ�£������ض���
*/			//�ض����ض����ˢ�¶�����תҳ�棬ѡһ�ּ��ɡ����߿�������ʱ��ǰ�߲��ܡ�
			res.setStatus(302);
			res.setHeader("Location","http://localhost:8080/day35/xiazai.html");
		}
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		doGet(req, res);//���ַ�ʽ������ͬ������ֱ�ӵ���
	}
}
