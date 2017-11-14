package pack08_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//动态代理的步骤（动态代理用到了类加载器和反射的知识）
class MyInvocationHandler implements InvocationHandler{//没有public

	Sing obj; //singer对象
	
	public MyInvocationHandler() {
	}
	

	public MyInvocationHandler(Sing obj) {
		this.obj = obj;
	}

	/*
	 *参数1:  代理类对象(经纪人对象),和我们无关
	 *参数2：  你要增强的哪个方法对象:sing(); --->Method method = getMethod("sing");
	 *参数3：  要给增强的方法传递的参数:sing()
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {//加强（重写）invoke()，返回值仍然是Object
		
		System.out.println("跳个舞");
		//先调用没有增强之前的方法
		Object result = method.invoke(obj, args);//返回值是Object
		
		System.out.println("捐个款");
		
		return result; //原来的方法返回什么，这里还返回什么
	}
	
}
public class Demo02Proxy {//有public

	public static void main(String[] args) {
		//动态代理之前
		Sing singer01 = new Singer01();
		singer01.sing();//起初的方法
		
		//使用代理类对歌手类的唱歌行为进行功能增强
		/*
		 * 参数1：被代理类的类加载器
		 * 参数2：被代理类实现的接口
		 */

		System.out.println("---------------");
		singer01 = myProxy(singer01);//将类进行增强
		singer01.sing();//调用增强后的方法
		
		System.out.println("---------------------");
		Sing singer02 = new Singer02();
		singer02 = myProxy(singer02);//将类进行增强
		singer02.sing();//调用增强后的方法
	}
	
	public static Sing myProxy(Sing singer){//定义增强的方法（在类里面定义）。传入的参数类型和返回值类型都是一样的，这里是Sing类
		Sing proxySinger = (Sing)Proxy.newProxyInstance(singer.getClass().getClassLoader(), //该类对象的类加载器//记得将返回值强制转型成Sing
	               singer.getClass().getInterfaces(),//该类对象实现的接口
	               new MyInvocationHandler(singer));//使用了该实现InvocationHandler接口的类的实例对象//第三个参数才是真正的加强类的地方
		
		return proxySinger;
	}

}
