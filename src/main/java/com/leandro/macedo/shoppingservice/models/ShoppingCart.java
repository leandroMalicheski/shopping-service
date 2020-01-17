package com.leandro.macedo.shoppingservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @AllArgsConstructor
@Document(collation = "shopping-cart")
public class ShoppingCart {

    private Object id;
    private User user;
    private List<Item> itemsList;
    private double itemsTotalValue;

    public ShoppingCart (ObjectId id, User user){
        this.id = id;
        this.user = user;
    }

    public ShoppingCart closePurchase(){
        if(this.itemsList != null) {
            this.itemsList = this.itemsList.stream().sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
            this.itemsList.forEach(tempItem -> {
                if(tempItem.getQty() > 1)
                    this.itemsTotalValue = this.itemsTotalValue + (tempItem.getValue()*tempItem.getQty());
                else
                    this.itemsTotalValue = this.itemsTotalValue + tempItem.getValue();
            });
        }
        return this;
    }

    public ShoppingCart addItem(Item item){
        if(itemsList != null) {
            Item foundItem = this.itemsList.stream().filter(
                    tempItem ->
                            item.getId().toHexString().equals(tempItem.getId().toHexString())
            ).findAny().orElse(null);

            if(foundItem != null){
                int currentQty = foundItem.getQty();
                foundItem.setQty(currentQty+1);
            }else
                this.itemsList.add(item);
        }else {
            this.itemsList = new ArrayList<>();
            this.itemsList.add(item);
        }
        return this;
    }

    public ShoppingCart removeItem(Item item){
        Item foundItem = this.itemsList.stream()
                .filter(
                        tempItem ->
                                item.getId().toHexString().equals(tempItem.getId().toHexString())
                ).findAny().orElse(null);

        if(foundItem != null && item.getQty() > 1){
            int currentlyQty = foundItem.getQty();
            item.setQty(currentlyQty-1);
        }else{
            this.itemsList.removeIf(
                        tempItem -> tempItem.getId().toHexString().equals(item.getId().toHexString())
                    );
        }
        return this;
    }
}
