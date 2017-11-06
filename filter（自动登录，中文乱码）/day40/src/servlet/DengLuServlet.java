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
			System.out.println("登录操作异常");
			e.printStackTrace();
		}
		if(boo==false){
			response.getWriter().write("账号或密码错误，将于三秒后跳转到登录页面");
			/*try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				System.out.println("线程睡眠异常");
				e.printStackTrace();
			}
			response.sendRedirect("/day40/login.jsp");//重定向的话要写项目名，因为是客户端路径
*/			/*request.getRequestDispatcher("/login.jsp").forward(request, response); 这个也行*/
		}else {
			/*response.getWriter().write("登陆成功");*/
			YongHu yongHu=new YongHu();
			yongHu.setUsername(request.getParameter("username"));
			yongHu.setPassword(request.getParameter("password"));
			if(request.getSession().getAttribute("YongHu")==null){//filter中没设置session的话才会在servlet中设置session
				request.getSession().setAttribute("YongHu",yongHu);//对象做session，设置session
				System.out.println(request.getParameter("autoLogin"));
				System.out.println("这是在servlet里面设置的session");
			}
			
			if(request.getParameter("autoLogin") != null){//如果勾选了自动登录的话
				System.out.println("呵呵");
				String string=request.getParameter("username")+":"+request.getParameter("password");
				String encodestr=URLEncoder.encode(string, "utf-8");//对中文cookie进行编码，否则会报异常：java.lang.IllegalArgumentException: Control character in cookie value or attribute
				Cookie cookie=new Cookie("acookie", encodestr);
				cookie.setMaxAge(60*60*24*7);//时间的设置必须在添加cookie之前
				response.addCookie(cookie);//添加cookie
				
				/*response.getWriter().write("添加了cookie");*/
				System.out.println("添加了cookie");
			}
			response.getWriter().write("登陆成功");
			/*request.getRequestDispatcher("/encoding.html");//转到登陆成功页面
*/		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);//doGet报错的话这里也会跟着报错
	}

}