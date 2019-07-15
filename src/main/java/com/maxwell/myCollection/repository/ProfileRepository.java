package com.maxwell.myCollection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.ProfileEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

	List<ProfileEntity> findAll();

	Optional<ProfileEntity> findById(Long id);

	Optional<ProfileEntity> findByUsername(String username);

}