package com.knoxx.mycollection.repository;


import com.knoxx.mycollection.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByUserId(String userId);

}