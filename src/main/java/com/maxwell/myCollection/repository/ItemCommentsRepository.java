package com.maxwell.myCollection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.ItemCommentsEntity;

@Repository
public interface ItemCommentsRepository extends JpaRepository<ItemCommentsEntity, Long> {

	List<ItemCommentsEntity> findAll();

	Optional<ItemCommentsEntity> findById(Long id);

}