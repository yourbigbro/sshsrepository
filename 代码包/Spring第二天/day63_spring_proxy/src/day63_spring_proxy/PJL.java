package day63_spring_proxy;

public class PJL implements KindWoman {

	@Override
	public void shout() {
		System.out.println("啊啊啊");

	}

	@Override
	public void paymoney(Double money) {
		System.out.println("潘金莲得到了"+money+"块钱");

	}
	
	public void givemoney(Double money) {
		System.out.println("潘金莲给出了"+money+"块钱");

	}

}
