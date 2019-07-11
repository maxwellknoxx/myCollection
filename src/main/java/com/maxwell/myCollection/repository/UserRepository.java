package com.maxwell.myCollection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findAll();

	Optional<UserEntity> findById(Long id);
	
	Optional<UserEntity> findByUsername(String username);
	
	Optional<UserEntity> findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}