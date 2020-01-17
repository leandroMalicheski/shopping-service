package com.leandro.macedo.shoppingservice.service;

import com.leandro.macedo.shoppingservice.models.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(ObjectId id);

    User updateUser(ObjectId id, User user);

    User createUser(User user);

    void deleteUser(ObjectId id);
}
