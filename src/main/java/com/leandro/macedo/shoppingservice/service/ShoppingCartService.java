package com.leandro.macedo.shoppingservice.service;

import com.leandro.macedo.shoppingservice.models.Item;
import com.leandro.macedo.shoppingservice.models.ShoppingCart;
import org.bson.types.ObjectId;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> getAllShoppingCarts();

    ShoppingCart addItemToShoppingCart(ObjectId id, Item item);

    ShoppingCart removeItemFromShoppingCart(ObjectId id, Item item);

    ShoppingCart closePurchase(ObjectId id);
}
