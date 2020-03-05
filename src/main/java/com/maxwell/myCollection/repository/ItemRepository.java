package com.maxwell.myCollection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maxwell.myCollection.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findAll();

	Optional<Item> findById(Long id);

	List<Item> findByCategoryId(Long id);

}