package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.Item;
import com.maxwell.myCollection.exception.EntityNotFoundException;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.ItemDTO;
import com.maxwell.myCollection.repository.ItemRepository;
import com.maxwell.myCollection.service.ItemService;
import com.maxwell.myCollection.utils.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository repository;

	@Override
	public List<ItemDTO> findAll() throws ResourceNotFoundException {
		List<Item> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ResourceNotFoundException(Item.class, "No item found");
		}
		return ItemMapper.getListDTO(list);
	}

	@Override
	public ItemDTO findById(Long id) {
		return ItemMapper.getDTO(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Item.class, "id", id.toString())));
	}

	@Override
	public ItemDTO addItem(Item item) {
		Item entity = repository.save(item);
		if (entity == null) {
			return null;
		}
		return ItemMapper.getDTO(entity);
	}

	@Override
	public ItemDTO updateItem(Item item) {
		Item entity = repository.save(item);
		if (entity == null) {
			return null;
		}
		return ItemMapper.getDTO(entity);
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
	public List<ItemDTO> findByCategoryId(Long id)  throws ResourceNotFoundException {
		List<Item> list = repository.findByCategoryId(id);
		if (list.isEmpty()) {
			throw new ResourceNotFoundException(Item.class, "No item found");
		}
		return ItemMapper.getListDTO(list);
	}

}