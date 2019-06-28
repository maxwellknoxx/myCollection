package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.UserEntity;

public interface UserService {

	List<UserEntity> findAll();

	Optional<UserEntity> findById(Long id);

	UserEntity updateUser(UserEntity user);

	void removeUser(Long id);

}