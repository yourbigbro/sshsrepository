package com.itheima;

public class Service {
	static String xiaoxi="转账成功";
	 
		 public static String caoZuo(String name1,String name2,Double money){
				try {
					//开启事务
					ThreadLocalUtils.start();
					System.out.println("成功了");
					Dao dao=new Dao();
					//假如不用ThreadLocal的话，就要将con连接对象当作参数传入到queryName方法中。有了ThreadLocal就不用传参数了，简便了编程。
					Account aa1=dao.queryName(name1);//service层调用dao层定义的方法并将整个过程封装成一个方法供app层使用。
					Account aa2=dao.queryName(name2);
					aa1.setAmoney(aa1.getAmoney()-money);
					aa2.setAmoney(aa2.getAmoney()+money);
					//假如不用ThreadLocal的话，就要将con连接对象当作参数传入到queryName方法中。有了ThreadLocal就不用传参数了，简便了编程。
					dao.updateMoney(name1,money);
					dao.updateMoney(name2,money);
					//提交事务,出错的话就回滚事务。
					ThreadLocalUtils.commit();
					return xiaoxi;
				} catch (Exception e) {
					e.printStackTrace();
					//回滚事务
					ThreadLocalUtils.rollback();
					xiaoxi="转账失败";
					return xiaoxi;
				}
				
				
			}
	
	
}
