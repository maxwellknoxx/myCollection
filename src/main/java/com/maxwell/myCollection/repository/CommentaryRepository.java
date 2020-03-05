package com.maxwell.myCollection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.Commentary;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

	List<Commentary> findAll();

	Optional<Commentary> findById(Long id);
	
	List<Commentary> findByItemId(Long id);
	
	

}