package com.leandro.macedo.shoppingservice.service.impl;

import com.leandro.macedo.shoppingservice.models.ShoppingCart;
import com.leandro.macedo.shoppingservice.models.User;
import com.leandro.macedo.shoppingservice.repositories.ShoppingCartRepository;
import com.leandro.macedo.shoppingservice.repositories.UserRepository;
import com.leandro.macedo.shoppingservice.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(ObjectId id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User createUser(User user) {
        user.setId(ObjectId.get());
        shoppingCartRepository.save(new ShoppingCart(ObjectId.get(), user));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(ObjectId id) {
        User userToBeDeleted = userRepository.findById(id);
        ShoppingCart cartToBeDeleted = shoppingCartRepository.findShoppingCartByUser(userToBeDeleted);
        userRepository.delete(userToBeDeleted);
        shoppingCartRepository.delete(cartToBeDeleted);
    }
}
