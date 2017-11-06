package com.itheima;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//配置注解，使该类成为取代配置文件的配置类
@Configuration
@ComponentScan("com.itheima")//开启包扫描并扫描指定的包
public class SpringConfiguration {

}
