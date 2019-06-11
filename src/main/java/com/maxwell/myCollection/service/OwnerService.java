package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.OwnerEntity;

public interface OwnerService {

	List<OwnerEntity> findAll();

	Optional<OwnerEntity> findById(Long id);

	OwnerEntity addOwner(OwnerEntity owner);

	OwnerEntity updateOwner(OwnerEntity owner);

	void removeOwner(Long id);

}