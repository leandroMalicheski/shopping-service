package com.leandro.macedo.shoppingservice.repositories.impl;

import com.leandro.macedo.shoppingservice.models.ShoppingCart;
import com.leandro.macedo.shoppingservice.models.User;
import com.leandro.macedo.shoppingservice.repositories.ShoppingCartRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepositoryCustom {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public ShoppingCart findShoppingCartByUser(User userToBeDeleted) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user.id").is(userToBeDeleted.getId()));

        return mongoOperations.findOne(query, ShoppingCart.class);
    }
}
