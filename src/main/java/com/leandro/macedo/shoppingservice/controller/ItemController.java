package com.leandro.macedo.shoppingservice.controller;

import com.leandro.macedo.shoppingservice.models.Item;
import com.leandro.macedo.shoppingservice.service.ItemService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "item")
public class ItemController {

    @Autowired
    ItemService service;

    @GetMapping
    public List<Item> getItems(){ return service.getItems(); }

    @GetMapping(value = "/{id}")
    public Item getItemById(@PathVariable("id") ObjectId id){
        return service.getItemById(id);
    }

    @PutMapping(value = "/{id}")
    public Item updateItem(@PathVariable("id") ObjectId id, @Valid @RequestBody Item item){
        return service.updateItem(id,item);
    }

    @PostMapping()
    public Item createItem(@Valid @RequestBody Item item) {
        return service.createItem(item);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteItem(@PathVariable ObjectId id) {
        service.deleteItem(id);
    }



}
