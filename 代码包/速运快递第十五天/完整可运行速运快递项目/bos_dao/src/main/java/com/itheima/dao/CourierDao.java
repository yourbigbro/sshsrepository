package com.itheima.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.crm.Customer;
import com.itheima.domain.Courier;

//注意，这上面没有注解
//在用到条件查询之后变成了多继承，又继承了一个JpaSpecificationExecutor接口。注意，接口只能继承接口不能实现接口。还可以多继承
public interface CourierDao extends JpaRepository<Courier, Integer> ,JpaSpecificationExecutor<Courier>{
	
	//注意，这些注解是写在接口中的，dao仍然是没有实现类只有接口
	//注意deltag的数据类型是char，也就是字符
	//只要不是查询就要提交事务(这是spring data jpa的规则)
	@Query("update Courier set deltag='1' where id=?")
	@Modifying
	void updateDeltag(Integer valueOf);

	//根据id查询符合条件的customer对象
	//List<Customer> findById();

}
