package com.knoxx.mycollection.repository;

import com.knoxx.mycollection.entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<Item, Long> {

	List<Item> findAll();

	Optional<Item> findById(Long id);

	Optional<Item> findByItemId(String itemId);

	List<Item> findByCategoryId(String categoryId);

	@Query(value = "{'_id' : ?0}", delete = true)
	void deleteById(String id);

}