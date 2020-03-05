package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.Item;
import com.maxwell.myCollection.model.ItemDTO;

public interface ItemService {

	List<ItemDTO> findAll();

	ItemDTO findById(Long id);
	
	List<ItemDTO> findByCategoryId(Long id);

	ItemDTO addItem(Item item);

	ItemDTO updateItem(Item item);

	Boolean removeItem(Long id);

}