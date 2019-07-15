package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.ProfileEntity;
import com.maxwell.myCollection.repository.ProfileRepository;
import com.maxwell.myCollection.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository repository;

	@Override
	public List<ProfileEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<ProfileEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public ProfileEntity addProfile(ProfileEntity profile) {
		return repository.save(profile);
	}

	@Override
	public ProfileEntity updateProfile(ProfileEntity profile) {
		return repository.save(profile);
	}

	@Override
	public void removeProfile(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<ProfileEntity> findByUsername(String username) {
		return repository.findByUsername(username);
	}

}