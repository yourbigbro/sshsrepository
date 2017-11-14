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
		System.out.println("session是："+yongHu);
		
		if(yongHu!=null){//检测是否存在session，决定了是否需要登录。若没有，再检测是否有cookie
			/*chain.doFilter(req, res);//有session就不需要登录
*/		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			System.out.println("使用session，无需登录");
			req.getRequestDispatcher("/encoding.html").forward(req,res);//直接跳转到登陆成功的页面，不必再放行
			//res.sendRedirect("encoding.html");
			
		}else {//不存在session的情况
			System.out.println("不存在session");
			Cookie sc = CookieUtils.searchCookie(req.getCookies(), "acookie");
			System.out.println("存在的cookie是："+sc);
			if(sc!=null){//存在cookie的情况
				//在使用cookie之前进行解码
				System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
				String decodestr=URLDecoder.decode(sc.getValue(), "utf-8");
				String username=decodestr.split(":")[0];
				String password=decodestr.split(":")[1];
				//进行登录
				DengLuService dle=new DengLuService();
				boolean boo = false;
				try {
					boo = dle.dengLu(username, password);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				if(boo){//cookie登陆成功
					//cookie登陆成功就要设置session
					System.out.println("使用cookie登陆成功");
					req.getRequestDispatcher("/encoding.html").forward(req,res);//直接跳转到登陆成功的页面，不必再放行
					YongHu yongHu2=new YongHu(username, password);
					req.getSession().setAttribute("YongHu", yongHu2);//设置session
					System.out.println("这是在filter里面设置的session");		
					//chain.doFilter(req, res);
				}else {//cookie登录失败
					System.out.println("使用cookie登录失败");
					res.getWriter().write("用户名或密码错误");
				}
			}else {//不存在cookie的情况
				chain.doFilter(req, res);//直接放行到DengLuServlet
			}
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
