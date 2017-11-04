修饰符的分类:
//===============权限修饰符====================
 
修饰符	       本类	  同一个包下(子类和无关类)  不同包下(子类)   不同包下(无关类)
private 	    Y		
默认		    Y		Y
protected	    Y		Y		                 Y
public		    Y		Y		                 Y			Y

//Animal ---> protected int num = 10;

//权限修饰符总结
public:   在任何地方都在访问
protected:这个修饰符为了子类设计的，只要是子类无论在哪里都能访问父类protected修饰的成员
默认 :    这个修饰符是为包设计的，这个修饰符修饰的成员离开的所在的包，不能访问
private:  这个修饰符是为类设计的，离开这个类，所有的成员都不能被访问

class Demo
{
	//成员变量
	private String name;
	private int age;

	//成员方法
	public void func(){	
	}
}

//=============其他修饰符=====================
class Demo
{
	static String country = "中国";

	String name;
	int age;


	static void func(){
		
	}
}



面向对象(类及其组成所使用的常见修饰符)
	A:修饰符：
		权限修饰符：private，默认的，protected，public
		状态修饰符：static， final
		抽象修饰符：abstract
		//abstract 和 private  :不能在一起
		//abstract 和 static   :不能在一起
		//abstract 和 final    :不能在一起
	B:类：
		权限修饰符：默认修饰符，public ,private(内部类),protected(内部类)
		状态修饰符：final
		抽象修饰符：abstract
		用的最多的就是：public
		//java中如果一个类使用public修饰，则这个java文件的名字必须和类名一致
		//一个java文件只能有public类
		内部类使用的特殊修饰符： static ， private
		
	C:成员变量：
		权限修饰符：private，默认的，protected，public
		状态修饰符：static，final
		用的最多的就是：private
		
		public static final int num = 10;
	D:构造方法：
		权限修饰符：private，默认的，protected，public
		用的最多的就是：public
		 private Student(){}
		 public Student(){}
		
	E:成员方法：
		权限修饰符：private，默认的，protected，public
		状态修饰符：static，final
		抽象修饰符：abstract
		
		用的最多的就是：public
		
		public static final void func(){}

	F:除此以外的最长组合规则：
		成员变量： public static final
		成员方法： public static 
		           public abstract
				   public final
				  
