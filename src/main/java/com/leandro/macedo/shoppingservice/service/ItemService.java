package com.leandro.macedo.shoppingservice.service;

import com.leandro.macedo.shoppingservice.models.Item;
import org.bson.types.ObjectId;

import java.util.List;

public interface ItemService {

    List<Item> getItems();

    Item getItemById(ObjectId id);

    Item updateItem(ObjectId id, Item item);

    Item createItem(Item item);

    void deleteItem(ObjectId id);
}
