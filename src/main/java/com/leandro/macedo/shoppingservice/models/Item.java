package com.leandro.macedo.shoppingservice.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter @Setter @AllArgsConstructor
public class Item {

    private ObjectId id;
    private int qty;
    private double value;
    private String name;

}
