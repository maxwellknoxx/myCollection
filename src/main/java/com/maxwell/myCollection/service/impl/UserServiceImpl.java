package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.UserEntity;
import com.maxwell.myCollection.model.UserModel;
import com.maxwell.myCollection.repository.UserRepository;
import com.maxwell.myCollection.service.UserService;
import com.maxwell.myCollection.utils.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<UserModel> findAll() {
		List<UserEntity> list = repository.findAll();
		if (list == null) {
			return null;
		}
		return UserMapper.entityToModelList(list);
	}

	@Override
	public UserModel findById(Long id) {
		UserEntity entity = repository.findById(id).orElseThrow();
		if (entity == null) {
			return null;
		}
		return UserMapper.entityToModel(entity);
	}

	public UserEntity getUser(Long id) {
		UserEntity entity = repository.findById(id).orElseThrow();
		if (entity == null) {
			return null;
		}
		return entity;
	}

	@Override
	public UserModel updateUser(UserEntity user) {
		UserEntity entity = repository.save(user);
		if (entity == null) {
			return null;
		}
		return UserMapper.entityToModel(entity);
	}

	public UserModel save(UserEntity user) {
		UserEntity entity = repository.save(user);
		if (entity == null) {
			return null;
		}
		return UserMapper.entityToModel(entity);
	}

	@Override
	public Boolean removeUser(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public UserModel findByEmail(String email) {
		UserEntity entity = repository.findByEmail(email).orElseThrow();
		if (entity == null) {
			return null;
		}
		return UserMapper.entityToModel(entity);
	}

	@Override
	public UserModel findByUsername(String username) {
		UserEntity entity = repository.findByUsername(username).orElseThrow(); //ver isso depois
		if (entity == null) {
			return null;
		}
		return UserMapper.entityToModel(entity);
	}

	public UserEntity getByEmail(String email) {
		UserEntity entity = repository.findByEmail(email).orElseThrow();
		if (entity == null) {
			return null;
		}
		return entity;
	}

}