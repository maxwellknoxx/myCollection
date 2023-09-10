package com.knoxx.mycollection.repository;

import com.knoxx.mycollection.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, Long> {

    Optional<Category> findByCategoryId(String categoryId);

    @Query(value = "{'_id' : ?0}", delete = true)
    void deleteById(String id);

}