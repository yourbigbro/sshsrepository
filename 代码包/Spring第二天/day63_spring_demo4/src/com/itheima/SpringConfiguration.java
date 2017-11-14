package com.itheima;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.itheima")
@Import(C3P0Utils.class)//引入其他类。用上了这句话之后就不用在C3P0Utils类上面加@Component("c3p0")了
public class SpringConfiguration {

}
