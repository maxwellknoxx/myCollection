package com.maxwell.myCollection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

	List<Reply> findAll();

	Optional<Reply> findById(Long id);

}