package com.itheima.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

//注意，是实现的接口而不是继承的某个父类
//自定义异常处理器。引用了自定义异常。
public class HandlExcepResol implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception arg3) {
		
		String msg;
		//判断抛出的异常是否是自定义异常(预期异常)
		if(arg3 instanceof MyException){
			String message = arg3.getMessage();
			msg=message;
		}else {
			msg="运行时异常";
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", msg);
		modelAndView.setViewName("error");
		//跳转到错误页面error.jsp
		return modelAndView;
	}

}
