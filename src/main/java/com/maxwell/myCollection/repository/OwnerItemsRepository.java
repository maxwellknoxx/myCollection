package com.maxwell.myCollection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.OwnerItemsEntity;

@Repository
public interface OwnerItemsRepository extends JpaRepository<OwnerItemsEntity, Long> {

	List<OwnerItemsEntity> findAll();

	Optional<OwnerItemsEntity> findById(Long id);

}