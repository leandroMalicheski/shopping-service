package com.leandro.macedo.shoppingservice.controller;

import com.leandro.macedo.shoppingservice.models.Item;
import com.leandro.macedo.shoppingservice.models.ShoppingCart;
import com.leandro.macedo.shoppingservice.service.ShoppingCartService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService service;

    @GetMapping()
    public List<ShoppingCart> getAllShoppingCarts(){
        return service.getAllShoppingCarts();
    }

    @PutMapping(value = "/addItem/{id}")
    public ShoppingCart addItem(@PathVariable("id") ObjectId id, @Valid @RequestBody Item item){
        return service.addItemToShoppingCart(id, item);
    }

    @PutMapping(value = "/removeItem/{id}")
    public ShoppingCart removeItem(@PathVariable("id") ObjectId id, @Valid @RequestBody Item item){
        return service.removeItemFromShoppingCart(id,item);
    }

    @GetMapping(value = "/closePurchase/{id}")
    public ShoppingCart closePurchase(@PathVariable("id") ObjectId id){
        return service.closePurchase(id);
    }

}
