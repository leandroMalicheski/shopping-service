package com.leandro.macedo.shoppingservice.repositories;

import com.leandro.macedo.shoppingservice.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findById(ObjectId id);
    User findByEmail(String email);
}
