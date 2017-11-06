package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//设置当前类为配置类
@Configuration
//开启包扫描
@ComponentScan("com.itheima")
//开启AOP注解扫描
@EnableAspectJAutoProxy
public class SpringConfiguration {//该类里面是空的
	
}
