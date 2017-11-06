package filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.YongHu;
import service.DengLuService;
import utils.CookieUtils;


public class LoginFilter implements Filter {

    
    public LoginFilter() {
       
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		YongHu yongHu=(YongHu)req.getSession().getAttribute("YongHu");
		System.out.println("session�ǣ�"+yongHu);
		
		if(yongHu!=null){//����Ƿ����session���������Ƿ���Ҫ��¼����û�У��ټ���Ƿ���cookie
			/*chain.doFilter(req, res);//��session�Ͳ���Ҫ��¼
*/		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			System.out.println("ʹ��session�������¼");
			req.getRequestDispatcher("/encoding.html").forward(req,res);//ֱ����ת����½�ɹ���ҳ�棬�����ٷ���
			//res.sendRedirect("encoding.html");
			
		}else {//������session�����
			System.out.println("������session");
			Cookie sc = CookieUtils.searchCookie(req.getCookies(), "acookie");
			System.out.println("���ڵ�cookie�ǣ�"+sc);
			if(sc!=null){//����cookie�����
				//��ʹ��cookie֮ǰ���н���
				System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
				String decodestr=URLDecoder.decode(sc.getValue(), "utf-8");
				String username=decodestr.split(":")[0];
				String password=decodestr.split(":")[1];
				//���е�¼
				DengLuService dle=new DengLuService();
				boolean boo = false;
				try {
					boo = dle.dengLu(username, password);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				if(boo){//cookie��½�ɹ�
					//cookie��½�ɹ���Ҫ����session
					System.out.println("ʹ��cookie��½�ɹ�");
					req.getRequestDispatcher("/encoding.html").forward(req,res);//ֱ����ת����½�ɹ���ҳ�棬�����ٷ���
					YongHu yongHu2=new YongHu(username, password);
					req.getSession().setAttribute("YongHu", yongHu2);//����session
					System.out.println("������filter�������õ�session");		
					//chain.doFilter(req, res);
				}else {//cookie��¼ʧ��
					System.out.println("ʹ��cookie��¼ʧ��");
					res.getWriter().write("�û������������");
				}
			}else {//������cookie�����
				chain.doFilter(req, res);//ֱ�ӷ��е�DengLuServlet
			}
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
