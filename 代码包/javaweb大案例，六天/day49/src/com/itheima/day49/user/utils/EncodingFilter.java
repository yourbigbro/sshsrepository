package com.itheima.day49.user.utils;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//用动态代理实现的乱码过滤器
public class EncodingFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //装饰者模式实现
        /*//0.强转
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        //对请求方式进行判断
        String method = req.getMethod();
        if("POST".equalsIgnoreCase(method)){
            //1.处理POST请求乱码问题
            req.setCharacterEncoding("utf-8");
            //2.放行
            chain.doFilter(req, resp);
        }else if("GET".equalsIgnoreCase(method)){
            //3.解决GET请求乱码问题
            MyHttpServletRequest myRequest = new MyHttpServletRequest(req);
            //4.放行
            chain.doFilter(myRequest, resp);
        }*/
        
        //动态代理模式实现
        //1.获得被代理对象(已存在，ServletRequest)
        final HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        resp.setContentType("text/html;charset=utf-8");        
        //2.创建代理对象
        HttpServletRequest myreq = (HttpServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //3.对指定方法进行增强(getParameter)
                if("getParameter".equals(method.getName())){
                    //4.只对GET请求进行处理
                    if("GET".equalsIgnoreCase(req.getMethod())){
                        //5.执行被代理对象里面的方法(getParameter)
                        String parameter = (String) method.invoke(req, args);
                        //6.对参数进行编码处理
                        parameter = new String(parameter.getBytes("iso8859-1"),"utf-8");
                        return parameter;
                    }else if("POST".equalsIgnoreCase(req.getMethod())){
                        req.setCharacterEncoding("utf-8");
                    }
                    //7.如果是其它方式，就执行原有的方法
                    return method.invoke(req, args);
                }
                //8.如果是其它方法被执行，就执行对应的方法
                return method.invoke(req, args);
            }
        });
        
        //9.放行(让程序执行目标类的方法)
        chain.doFilter(myreq, resp);
        
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}