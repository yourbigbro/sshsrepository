package com.itheima.service;

import java.util.List;

import com.itheima.pojo.Items;

public interface ItemsService {

	List<Items> findAllItems();
	
	Items findItemsById(Integer id);
	
	void updateItemsById(Items items);

	void deleteItemsById(String[] ids);

	void updateItems(List<Items> list);
}
