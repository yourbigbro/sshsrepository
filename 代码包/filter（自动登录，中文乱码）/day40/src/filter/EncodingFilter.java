package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.MyHttpServletRequest;


public class EncodingFilter implements Filter {

    public EncodingFilter() {
        
    }

	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		System.out.println("经过了encoding过滤器");
		res.setContentType("text/html;charset=utf-8");
		
		
		
		String method=req.getMethod();
		if(method.equalsIgnoreCase("post")){
			req.setCharacterEncoding("utf-8");
			chain.doFilter(req, res);//注意参数是转换之后的参数，并且参数是两个实例对象
		}else {
			MyHttpServletRequest mhsr=new MyHttpServletRequest(req);
			chain.doFilter(mhsr, res);
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
