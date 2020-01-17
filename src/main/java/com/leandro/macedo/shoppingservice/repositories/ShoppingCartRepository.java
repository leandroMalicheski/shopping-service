package com.leandro.macedo.shoppingservice.repositories;

import com.leandro.macedo.shoppingservice.models.ShoppingCart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String>, ShoppingCartRepositoryCustom {
    ShoppingCart findById(ObjectId id);
}
