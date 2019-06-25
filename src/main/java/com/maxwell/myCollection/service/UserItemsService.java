package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.UserItemsEntity;

public interface UserItemsService {

	List<UserItemsEntity> findAll();

	Optional<UserItemsEntity> findById(Long id);

	UserItemsEntity addOwnerItems(UserItemsEntity owneritems);

	UserItemsEntity updateOwnerItems(UserItemsEntity owneritems);

	void removeOwnerItems(Long id);

}