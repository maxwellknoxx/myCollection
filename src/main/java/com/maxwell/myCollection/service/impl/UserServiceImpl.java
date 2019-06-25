package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.UserEntity;
import com.maxwell.myCollection.repository.UserRepository;
import com.maxwell.myCollection.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<UserEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<UserEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public UserEntity addOwner(UserEntity owner) {
		return repository.save(owner);
	}

	@Override
	public UserEntity updateOwner(UserEntity owner) {
		return repository.save(owner);
	}

	@Override
	public void removeOwner(Long id) {
		repository.deleteById(id);
	}

}