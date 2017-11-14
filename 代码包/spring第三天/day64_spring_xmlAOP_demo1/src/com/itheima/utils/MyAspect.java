package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;

//aspect切面类
public class MyAspect {
	
		//advice:增强
		public void privilege(){
			System.out.println("权限校验方法执行了。。。");
		}
		
		//后置增强
		public void afterReturning(){
			System.out.println("后置增强执行了。。。");
		}
		
		//环绕通知，只有这个里面有参数，参数类型是ProceedingJoinPoint
		public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
			System.out.println("目标方法执行前。。。"+System.nanoTime());//前置增强
			proceedingJoinPoint.proceed();//执行目标方法
			System.out.println("目标方法执行后。。。"+System.nanoTime());//后置增强
		}
		
		//异常通知
		public void afterThrowing(){
			System.out.println("异常增强执行了。。。");
		}
		
		//最终通知
		public void after(){
			System.out.println("最终增强执行了。。。");
		}
}
