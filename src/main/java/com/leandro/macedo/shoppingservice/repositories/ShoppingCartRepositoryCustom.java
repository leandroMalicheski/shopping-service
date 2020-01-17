package com.leandro.macedo.shoppingservice.repositories;

import com.leandro.macedo.shoppingservice.models.ShoppingCart;
import com.leandro.macedo.shoppingservice.models.User;

public interface ShoppingCartRepositoryCustom {

    ShoppingCart findShoppingCartByUser(User userToBeDeleted);
}
