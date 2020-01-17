package com.leandro.macedo.shoppingservice.service.impl;

import com.leandro.macedo.shoppingservice.models.Item;
import com.leandro.macedo.shoppingservice.repositories.ItemRepository;
import com.leandro.macedo.shoppingservice.service.ItemService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository repository;

    @Override
    public List<Item> getItems() { return repository.findAll(); }

    @Override
    public Item getItemById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public Item updateItem(ObjectId id, Item item) {
        item.setId(id);
        return repository.save(item);
    }

    @Override
    public Item createItem(Item item) {
        item.setId(ObjectId.get());
        return repository.save(item);
    }

    @Override
    public void deleteItem(ObjectId id) {
        repository.delete(repository.findById(id));
    }
}
