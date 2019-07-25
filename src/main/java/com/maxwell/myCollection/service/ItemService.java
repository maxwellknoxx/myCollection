package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.ItemEntity;
import com.maxwell.myCollection.model.Item;

public interface ItemService {

	List<Item> findAll();

	Item findById(Long id);
	
	List<Item> findByCategoryId(Long id);

	Item addItem(ItemEntity item);

	Item updateItem(ItemEntity item);

	void removeItem(Long id);

}