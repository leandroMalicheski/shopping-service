package com.leandro.macedo.shoppingservice.controller;

import com.leandro.macedo.shoppingservice.models.User;
import com.leandro.macedo.shoppingservice.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") ObjectId id){
        return service.getUserById(id);
    }

    @PutMapping(value = "/{id}")
    public User updateUser(@PathVariable("id") ObjectId id, @Valid @RequestBody User user){
        return service.updateUser(id,user);
    }

    @PostMapping()
    public User createUser(@Valid @RequestBody User user) {
        return service.createUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable ObjectId id) {
        service.deleteUser(id);
    }

}
