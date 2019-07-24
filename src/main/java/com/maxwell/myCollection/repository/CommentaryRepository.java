package com.maxwell.myCollection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.CommentaryEntity;

@Repository
public interface CommentaryRepository extends JpaRepository<CommentaryEntity, Long> {

	List<CommentaryEntity> findAll();

	Optional<CommentaryEntity> findById(Long id);
	
	List<CommentaryEntity> findByItemId(Long id);
	
	

}