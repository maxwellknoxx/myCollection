package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.OwnerEntity;
import com.maxwell.myCollection.repository.OwnerRepository;
import com.maxwell.myCollection.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerRepository repository;

	@Override
	public List<OwnerEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<OwnerEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public OwnerEntity addOwner(OwnerEntity owner) {
		return repository.save(owner);
	}

	@Override
	public OwnerEntity updateOwner(OwnerEntity owner) {
		return repository.save(owner);
	}

	@Override
	public void removeOwner(Long id) {
		repository.deleteById(id);
	}

}