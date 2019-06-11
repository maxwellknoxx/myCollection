package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.OwnerItemsEntity;

public interface OwnerItemsService {

	List<OwnerItemsEntity> findAll();

	Optional<OwnerItemsEntity> findById(Long id);

	OwnerItemsEntity addOwnerItems(OwnerItemsEntity owneritems);

	OwnerItemsEntity updateOwnerItems(OwnerItemsEntity owneritems);

	void removeOwnerItems(Long id);

}