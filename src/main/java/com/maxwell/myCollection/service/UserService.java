package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.User;
import com.maxwell.myCollection.model.UserModelDTO;

public interface UserService {

	List<UserModelDTO> findAll();

	UserModelDTO findById(Long id);
	
	UserModelDTO findByEmail(String email);

	UserModelDTO findByUsername(String username);
	
	UserModelDTO updateUser(User user);

	Boolean removeUser(Long id);

}