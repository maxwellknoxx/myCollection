package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.ProfileEntity;

public interface ProfileService {

	List<ProfileEntity> findAll();

	Optional<ProfileEntity> findById(Long id);
	
	Optional<ProfileEntity> findByUsername(String username);

	ProfileEntity addProfile(ProfileEntity profile);

	ProfileEntity updateProfile(ProfileEntity profile);

	void removeProfile(Long id);

}