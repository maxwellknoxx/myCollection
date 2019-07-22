package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.ItemEntity;

public interface ItemService {

	List<ItemEntity> findAll();

	Optional<ItemEntity> findById(Long id);
	
	List<ItemEntity> findByCategoryId(Long id);

	ItemEntity addItem(ItemEntity item);

	ItemEntity updateItem(ItemEntity item);

	void removeItem(Long id);

}