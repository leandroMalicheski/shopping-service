package com.leandro.macedo.shoppingservice.service.impl;

import com.leandro.macedo.shoppingservice.models.Item;
import com.leandro.macedo.shoppingservice.models.ShoppingCart;
import com.leandro.macedo.shoppingservice.repositories.ShoppingCartRepository;
import com.leandro.macedo.shoppingservice.service.ShoppingCartService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    ShoppingCartRepository repository;

    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return repository.findAll();
    }

    @Override
    public ShoppingCart addItemToShoppingCart(ObjectId id, Item item) {
        ShoppingCart shoppingCart = repository.findById(id);
        repository.save(shoppingCart.addItem(item));
        return shoppingCart;
    }

    @Override
    public ShoppingCart removeItemFromShoppingCart(ObjectId id, Item item) {
        ShoppingCart shoppingCart = repository.findById(id);
        repository.save(shoppingCart.removeItem(item));
        return shoppingCart;
    }

    @Override
    public ShoppingCart closePurchase(ObjectId id) {
        ShoppingCart shoppingCart = repository.findById(id);
        repository.save(shoppingCart.closePurchase());
        return shoppingCart;
    }
}
