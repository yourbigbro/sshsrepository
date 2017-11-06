package day63_spring_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//动态代理的实现(工具类)
public class ProxyFactory {
	public static Object getProxy(final Class clazz){
		//输入一个类并返回一个代理类(增强类)
		//第一个参数是当前类的类加载器，第二个参数是当前类实现的所有接口，第三个是如何调用目标对象中的方法
		Object proxy=Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),new InvocationHandler() {
			
			//既然是override就会自动打出来
			@Override
			//第一个参数是代理参数本身的引用，一般不用
			//第二个参数是代理对象的方法对象(也可以说是被代理对象的方法对象，毕竟他们都有相同的方法)
			//第三个参数是方法的参数数组(注意，类型被转换成了object，不再是double，因此要再转换成double)
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				//注意，编译的时候看左边执行的时候看右边，所以输出args[0]的类型的话就是double但是却不能用double的方法，因为编译时看的是object，而object没有double的方法
				args[0]=Double.parseDouble(args[0]+"")/2;
				
				//检测当前调用的方法的名称，可以用于区分不同的方法，以调用不同的逻辑。否则，所有的方法都会执行同一种逻辑
				System.out.println(method.getName());
				Object o = method.invoke(clazz.newInstance(), args);
				return o;
			}
		});
		return proxy;
	}
}
