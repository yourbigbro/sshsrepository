package cn.itcast.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

//getEmpsByDeptno方法是EmpDao类中的方法
public class EmpDao {
	//这是连接对象
	Connection conn = null;
	//该对象是普通的通用的sql语言使用
	//这是已编译的sql语句
	PreparedStatement stat = null;
	//该对象是pl/sql语言使用
	//CallableStatement 继承 Statement 的方法（它们用于处理一般的 SQL 语句），
	//还继承了 PreparedStatement 的方法（它们用于处理 IN 参数）。
	CallableStatement stat2 = null;
	//这是结果集
	ResultSet rs = null;
	
	
	//EmpDao类中的第一个方法及其测试方法
	
	
	/**
	 * 根据部门编号 查询用户信息
	 * @param deptno  部门编号
	 */
	//这个方法会在下面的main测试函数里面调用
	public void getEmpsByDeptno(Long deptno){
		try {
			//建立与数据库的连接(调用工具类中的静态方法)
			conn = BaseDao.getConn();////////////////////////////////////////////////////////////////引用BaseDap类的静态方法
			
			/*该 PreparedStatement接口继承Statement，并与之在两方面有所不同：
			PreparedStatement 实例包含已编译的 SQL 语句。这就是使语句“准备好”。
			包含于 PreparedStatement 对象中的 SQL 语句可具有一个或多个 IN 参数。
			IN参数的值在 SQL 语句创建时未被指定。相反的，该语句为每个 IN 参数保留一个问号（“？”）作为占位符。
			每个问号的值必须在该语句执行之前，通过适当的setXXX 方法来提供。
			由于 PreparedStatement 对象已预编译过，所以其执行速度要快于 Statement 对象。
			因此，多次执行的 SQL 语句经常创建为 PreparedStatement 对象，以提高效率。
			作为 Statement 的子类，PreparedStatement 继承了 Statement 的所有功能。
			另外它还添加了一整套方法，用于设置发送给数据库以取代 IN 参数占位符的值。
			同时，三种方法 execute、 executeQuery 和 executeUpdate 已被更改以使之不再需要参数。
			这些方法的 Statement 形式（接受 SQL 语句参数的形式）不应该用于 PreparedStatement 对象。*/
			PreparedStatement stat = conn.prepareStatement("select * from emp where deptno= ?");
			//设置问号?所对应的参数
			stat.setLong(1, deptno);
			//查询数据库并返回结果
			rs = stat.executeQuery();
			//循环读取每一条数据
			while(rs.next()){
				//查询该行的对应索引和列名的属性值(也就是数据)
				System.out.println(rs.getLong(1) + "," + rs.getString("ename"));
			}
		} catch (SQLException e) {
			//出错的话就打印错误信息
			e.printStackTrace();
		}finally {
			//调用BaseBao类中的静态方法
			//调用re,stat,conn三个对象的close()方法用于关闭连接
			BaseDao.closeAll(rs, stat, conn);/////////////////////////////////////////////////////////////引用BaseDao类的静态方法
		}		
	}
	
	public static void main(String[] args) {
		//想要调用getEmpsByDeptno方法应该是不用创建(new)EmpDao对象，直接调用就行，就像下面的三个方法那样
		EmpDao empDao = new EmpDao();
		empDao.getEmpsByDeptno(10L);
	}
	
	
	
	//EmpDao类中的第二个方法及其测试方法
	
	
		
	/////jdbc连接   存储过程
	/**
	 * 根据员工编号 获取员工年薪
	 * @param empno  员工编号
	 * {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
       {call <procedure-name>[(<arg1>,<arg2>, ...)]}
	 */
	public void getYearsSalByEmpno(Long empno){
		try {
			conn = BaseDao.getConn();
			stat2 = conn.prepareCall("{call get_years_sals1(?,?)}");
			//给第一个参数赋值
			stat2.setLong(1, empno);
			//声明第二个参数的类型
			stat2.registerOutParameter(2, OracleTypes.NUMBER);
			stat2.execute();
			System.out.println("年薪为" + stat2.getLong(2));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeAll(rs, stat2, conn);
		}		
	}
	
	@Test
	public void testGetYearsSalByEmpno(){
		getYearsSalByEmpno(7788L);
	}
	
	
	//EmpDao类中的第三个方法及其测试方法
	
	
	
	///调用存储函数
	/**
	 * 根据员工编号 获取员工年薪
	 * @param empno  员工编号
	 * {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
       {call <procedure-name>[(<arg1>,<arg2>, ...)]}
	 */
	public void getYearsSalByEmpnoFun(Long empno){
		try {
			conn = BaseDao.getConn();
			stat2 = conn.prepareCall("{?= call get_years_sal_fun(?)}");
			//给第二个参数赋值
			stat2.setLong(2, empno);
			//声明第一个参数的类型
			stat2.registerOutParameter(1, OracleTypes.NUMBER);
			stat2.execute();
			System.out.println("年薪为" + stat2.getLong(1));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeAll(rs, stat2, conn);
		}		
	}
	
	@Test
	public void testGetYearsSalByEmpnoFun(){
		getYearsSalByEmpnoFun(7369L);
	}
	
	
	
	//EmpDao类中的第四个方法及其测试方法
	
	
	//用jdbc 调用返回值是游标的存储过程
	public void getEmpByDeptno(Long deptno){
		try {
			//这个类里边每执行一个方法(或进行一次操作)都要进行创建连接，然后在最后关闭连接
			conn = BaseDao.getConn();
			stat2 = conn.prepareCall("{call get_emps_by_deptno(?,?)}");
			//给第一个参数赋值
			stat2.setLong(1, deptno);
			//声明第二个参数的类型
			stat2.registerOutParameter(2, OracleTypes.CURSOR);
			stat2.execute();
			rs = ((OracleCallableStatement)stat2).getCursor(2);
			while (rs.next()) {
				System.out.println(rs.getLong(1) + "," + rs.getString("ename"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			BaseDao.closeAll(rs, stat2, conn);
		}		
	}
	
	@Test
	//本类中的测试方法调用本类中的另一个方法。直接写方法不写对象是因为省略了this
	public void testGetEmpByDeptno(){
		getEmpByDeptno(20L);
	}
	
}
