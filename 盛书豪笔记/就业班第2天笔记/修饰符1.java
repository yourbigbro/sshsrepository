���η��ķ���:
//===============Ȩ�����η�====================
 
���η�	       ����	  ͬһ������(������޹���)  ��ͬ����(����)   ��ͬ����(�޹���)
private 	    Y		
Ĭ��		    Y		Y
protected	    Y		Y		                 Y
public		    Y		Y		                 Y			Y

//Animal ---> protected int num = 10;

//Ȩ�����η��ܽ�
public:   ���κεط����ڷ���
protected:������η�Ϊ��������Ƶģ�ֻҪ���������������ﶼ�ܷ��ʸ���protected���εĳ�Ա
Ĭ�� :    ������η���Ϊ����Ƶģ�������η����εĳ�Ա�뿪�����ڵİ������ܷ���
private:  ������η���Ϊ����Ƶģ��뿪����࣬���еĳ�Ա�����ܱ�����

class Demo
{
	//��Ա����
	private String name;
	private int age;

	//��Ա����
	public void func(){	
	}
}

//=============�������η�=====================
class Demo
{
	static String country = "�й�";

	String name;
	int age;


	static void func(){
		
	}
}



�������(�༰�������ʹ�õĳ������η�)
	A:���η���
		Ȩ�����η���private��Ĭ�ϵģ�protected��public
		״̬���η���static�� final
		�������η���abstract
		//abstract �� private  :������һ��
		//abstract �� static   :������һ��
		//abstract �� final    :������һ��
	B:�ࣺ
		Ȩ�����η���Ĭ�����η���public ,private(�ڲ���),protected(�ڲ���)
		״̬���η���final
		�������η���abstract
		�õ����ľ��ǣ�public
		//java�����һ����ʹ��public���Σ������java�ļ������ֱ��������һ��
		//һ��java�ļ�ֻ����public��
		�ڲ���ʹ�õ��������η��� static �� private
		
	C:��Ա������
		Ȩ�����η���private��Ĭ�ϵģ�protected��public
		״̬���η���static��final
		�õ����ľ��ǣ�private
		
		public static final int num = 10;
	D:���췽����
		Ȩ�����η���private��Ĭ�ϵģ�protected��public
		�õ����ľ��ǣ�public
		 private Student(){}
		 public Student(){}
		
	E:��Ա������
		Ȩ�����η���private��Ĭ�ϵģ�protected��public
		״̬���η���static��final
		�������η���abstract
		
		�õ����ľ��ǣ�public
		
		public static final void func(){}

	F:������������Ϲ���
		��Ա������ public static final
		��Ա������ public static 
		           public abstract
				   public final
				  
