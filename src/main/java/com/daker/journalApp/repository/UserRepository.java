package com.daker.journalApp.repository;

import com.daker.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User , ObjectId> {
    User findByUsername(String username);

}
