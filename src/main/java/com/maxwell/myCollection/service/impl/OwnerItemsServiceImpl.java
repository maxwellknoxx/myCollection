package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.OwnerItemsEntity;
import com.maxwell.myCollection.repository.OwnerItemsRepository;
import com.maxwell.myCollection.service.OwnerItemsService;

@Service
public class OwnerItemsServiceImpl implements OwnerItemsService {

	@Autowired
	private OwnerItemsRepository repository;

	@Override
	public List<OwnerItemsEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<OwnerItemsEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public OwnerItemsEntity addOwnerItems(OwnerItemsEntity owneritems) {
		return repository.save(owneritems);
	}

	@Override
	public OwnerItemsEntity updateOwnerItems(OwnerItemsEntity owneritems) {
		return repository.save(owneritems);
	}

	@Override
	public void removeOwnerItems(Long id) {
		repository.deleteById(id);
	}

}