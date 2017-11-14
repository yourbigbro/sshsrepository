package com.itheima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.mapper.ItemsMapper;
import com.itheima.pojo.Items;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {
	
	@Autowired
	private ItemsMapper itemsMapper;

	//该方法用于查找所有的Items对象的集合
	@Override
	public List<Items> findAllItems() {
		
		List<Items> list = itemsMapper.selectByExample(null);
		return list;
	}

	//根据id查找对应的Items对象
	@Override
	public Items findItemsById(Integer id) {
		
		Items items = itemsMapper.selectByPrimaryKey(id);
		return items;
	}

	//该方法用于保存修改后的items对象到数据库
	@Override
	public void updateItemsById(Items items) {
		
		itemsMapper.updateByPrimaryKey(items);
		
	}

}
