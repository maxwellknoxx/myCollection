package com.maxwell.myCollection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.UserItemsEntity;

@Repository
public interface UserItemsRepository extends JpaRepository<UserItemsEntity, Long> {

	List<UserItemsEntity> findAll();

	Optional<UserItemsEntity> findById(Long id);

}