package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.ItemEntity;
import com.maxwell.myCollection.model.Item;
import com.maxwell.myCollection.repository.ItemRepository;
import com.maxwell.myCollection.service.ItemService;
import com.maxwell.myCollection.utils.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository repository;

	@Override
	public List<Item> findAll() {
		List<ItemEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return ItemMapper.convertEntityToModelList(list);
	}

	@Override
	public Item findById(Long id) {
		ItemEntity item = repository.findById(id).orElse(null);
		if (item == null) {
			return null;
		}
		return ItemMapper.convertEntityToModel(item);

	}

	@Override
	public Item addItem(ItemEntity item) {
		ItemEntity entity = repository.save(item);
		if (entity == null) {
			return null;
		}
		return ItemMapper.convertEntityToModel(entity);
	}

	@Override
	public Item updateItem(ItemEntity item) {
		ItemEntity entity = repository.save(item);
		if (entity == null) {
			return null;
		}
		return ItemMapper.convertEntityToModel(entity);
	}

	@Override
	public Boolean removeItem(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Item> findByCategoryId(Long id) {
		List<ItemEntity> list = repository.findByCategoryId(id);
		if(list.isEmpty()) {
			return null;
		}
		return ItemMapper.convertEntityToModelList(list);
	}

}