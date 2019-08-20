package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.UserEntity;
import com.maxwell.myCollection.model.UserModel;

public interface UserService {

	List<UserModel> findAll();

	UserModel findById(Long id);
	
	UserModel findByEmail(String email);

	UserModel findByUsername(String username);
	
	UserModel updateUser(UserEntity user);

	Boolean removeUser(Long id);

}