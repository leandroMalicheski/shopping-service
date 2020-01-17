package com.leandro.macedo.shoppingservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter @Setter @AllArgsConstructor
public class User {

    private ObjectId id;
    private String name;
    private String email;

}
