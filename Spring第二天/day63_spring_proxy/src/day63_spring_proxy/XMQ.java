package day63_spring_proxy;

public class XMQ {

	public static void main(String[] args) {
		KindWoman wp = (KindWoman)ProxyFactory.getProxy(PJL.class);
		wp.givemoney(10.0);
		wp.paymoney(10.0);
	}

}
