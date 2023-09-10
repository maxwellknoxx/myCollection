package com.knoxx.mycollection.service;

import com.knoxx.mycollection.entity.User;
import com.knoxx.mycollection.exception.EntityNotFoundException;
import com.knoxx.mycollection.interceptor.ResourceNotFoundException;
import com.knoxx.mycollection.model.UserDto;
import com.knoxx.mycollection.repository.UserRepository;
import com.knoxx.mycollection.utils.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    private final UserMapper userMapper;

    public UserService(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public List<UserDto> findAll() throws ResourceNotFoundException {
        List<User> usersList = repository.findAll();

        if (usersList.isEmpty()) {
            throw new ResourceNotFoundException(User.class, "Message={No users found}");
        }

        return userMapper.toDtoList(usersList);
    }

    public UserDto findByUserId(String userId) {
        return userMapper.toDto(repository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", userId)));
    }

}