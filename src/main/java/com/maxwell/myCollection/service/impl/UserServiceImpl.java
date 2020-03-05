package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.User;
import com.maxwell.myCollection.exception.EntityNotFoundException;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.UserModelDTO;
import com.maxwell.myCollection.repository.UserRepository;
import com.maxwell.myCollection.service.UserService;
import com.maxwell.myCollection.utils.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public List<UserModelDTO> findAll() throws ResourceNotFoundException {
		List<User> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ResourceNotFoundException(User.class, "No user found");
		}
		return UserMapper.getListDTO(list);
	}

	@Override
	public UserModelDTO findById(Long id) {
		return UserMapper.getDTO(repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString())));
	}

	public User getUser(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class, "id", id.toString()));
	}

	@Override
	public UserModelDTO updateUser(User user) {
		User entity = repository.save(user);
		if (entity == null) {
			return null;
		}
		return UserMapper.getDTO(entity);
	}

	public UserModelDTO save(User user) {
		User entity = repository.save(user);
		if (entity == null) {
			return null;
		}
		return UserMapper.getDTO(entity);
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
	public UserModelDTO findByEmail(String email) {
		return UserMapper.getDTO(repository.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException(User.class, "Email -> ", email)));
	}

	public User getByEmail(String email) {
		return repository.findByEmail(email)
				.orElseThrow(() -> new EntityNotFoundException(User.class, "Email -> ", email));
	}

	@Override
	public UserModelDTO findByUsername(String username) {
		return UserMapper.getDTO(repository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException(User.class, "Username -> ", username)));
	}

}