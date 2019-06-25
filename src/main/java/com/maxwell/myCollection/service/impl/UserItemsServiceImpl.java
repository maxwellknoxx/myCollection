package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.UserItemsEntity;
import com.maxwell.myCollection.repository.UserItemsRepository;
import com.maxwell.myCollection.service.UserItemsService;

@Service
public class UserItemsServiceImpl implements UserItemsService {

	@Autowired
	private UserItemsRepository repository;

	@Override
	public List<UserItemsEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<UserItemsEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public UserItemsEntity addOwnerItems(UserItemsEntity owneritems) {
		return repository.save(owneritems);
	}

	@Override
	public UserItemsEntity updateOwnerItems(UserItemsEntity owneritems) {
		return repository.save(owneritems);
	}

	@Override
	public void removeOwnerItems(Long id) {
		repository.deleteById(id);
	}

}