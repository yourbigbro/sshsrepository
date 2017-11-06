package com.itheima.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.SubAreaDao;
import com.itheima.domain.FixedArea;
import com.itheima.domain.SubArea;
import com.itheima.service.FixedAreaService;
import com.itheima.service.SubAreaService;

@Service("subAreaService")
@Transactional
public class SubAreaServiceImpl implements SubAreaService {
	
	@Autowired
	private SubAreaDao subAreaDao;

	//该方法用于查找所有的SubArea对象
	@Override
	public List<SubArea> findAll() {
		return subAreaDao.findAll();
	}

	//该方法用于分页查询SubArea对象
	//当接口中的方法和实现类中的方法对不上时，实现类中的方法会报错，接口中的方法不会报错
	@Override
	public Page<SubArea> findAll(Pageable pageable) {
		return subAreaDao.findAll(pageable);
	}

	//该方法用于保存新的分区数据(直接将装有数据的SubArea对象当参数传递过去让spring data jpa进行操作)
	@Override
	public void save(SubArea model) {
		//代替用户设置随机的id。注意，要将数字转换成字符串
		model.setId(UUID.randomUUID().toString());
		subAreaDao.save(model);
	}

	//该方法用于查询没有关联定区的分区对象
	@Override
	public List<SubArea> findByFixedAreaIsNull() {
		return subAreaDao.findByFixedAreaIsNull();
		
	}

	//该方法用于查询定区为某值的分区对象
	@Override
	public List<SubArea> findByFixedAreaIsSomeone(String id) {
		//创建areas集合用于盛放符合条件的集合
		List<SubArea> areas=new ArrayList<>();
		List<SubArea> allArea = this.findAll();
		//逐个遍历得到的每一个对象
		for (SubArea subArea : allArea) {
			//先检测对象是否存在才能检测对象的属性是否符合要求
			//注意要用equals不能用==，否则永远也不相等，因为比较的是内存地址而不是值
			if(subArea.getFixedArea()!=null&&subArea.getFixedArea().getId().equals(id)){
				areas.add(subArea);
			}
		}
		
		SubArea ssSubArea=areas.get(0);
		return areas;
	}

	//根据分区id查找对应的分区对象
	@Override
	public SubArea findOne(String string) {
		return subAreaDao.findOne(string);
		
	}
	
	@Autowired
	private FixedAreaService fixedAreaService;

	//该方法用于解除定区与分区之间的关联
	@Override
	public void removeRelationship(String idss) {
		
		List<SubArea> list=this.findByFixedAreaIsSomeone(idss);
		//将得到的list集合中的定区属性与分区解除关联(调用的就是正下方的方法)(也就是将分区中的定区属性设置为null)
		for (SubArea subArea : list) {
			subArea.setFixedArea(null);
		}
		//下面的两行代码根据前台传来的定区id:idss从数据库中查询到相应的定区对象，然后将定区的分区属性设置为null
		FixedArea fa = fixedAreaService.findOne(idss);
		//清除了定区中的分区属性
		//注意不能将他设置成nul，而应该设置成空的hashset,否则不能再装分区，同时也不符和逻辑
		fa.setSubareas(new HashSet<SubArea>());
	}

	//该方法用于查询饼状图所需要的信息
	@Override
	public List<Object[]> listPie() {
		return subAreaDao.listPie();
	}

}
