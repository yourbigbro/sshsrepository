package com.itheima.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//aspect切面类
//没了配置文件之后将配置文件里面的配置放在了这个存放加强方法的MyAspect类里面
@Component("MyAspect")//虽然没有地方引用MyAspect但一定要有这句话，否则无法扫描MyAspect类
@Aspect//配置AOP
public class MyAspect {
	
		//advice:增强
		@Before("pointcut1()")
		public void privilege(){
			System.out.println("权限校验方法执行了。。。");
		}
		
		//后置增强
		@AfterReturning("pointcut2()")
		public void afterReturning(){
			System.out.println("后置增强执行了。。。");
		}
		
		//环绕通知，只有这个里面有参数，参数类型是ProceedingJoinPoint
		@Around("pointcut3()")
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
		
		@Pointcut("execution(* com.itheima.service.impl.*.add*(..))")
		public void pointcut1(){}//注意这个方法里面没有任何东西
		
		@Pointcut("execution(* com.itheima.service.impl.*.update*(..))")
		public void pointcut2(){}
		
		@Pointcut("execution(* com.itheima.service.impl.*.delete*(..))")
		public void pointcut3(){}
}
