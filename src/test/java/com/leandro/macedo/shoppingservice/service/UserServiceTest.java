package com.leandro.macedo.shoppingservice.service;

import com.leandro.macedo.shoppingservice.models.ShoppingCart;
import com.leandro.macedo.shoppingservice.models.User;
import com.leandro.macedo.shoppingservice.repositories.ShoppingCartRepository;
import com.leandro.macedo.shoppingservice.repositories.UserRepository;
import com.leandro.macedo.shoppingservice.service.impl.UserServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;

@SpringBootTest
public class UserServiceTest {

    private static final String USER_NAME = "userName";
    private static final String USER_EMAIL = "usermName@email.com";

    @Mock
    UserRepository userRepository;

    @Mock
    ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    UserService service = new UserServiceImpl();

    @Test
    public void getUsers(){
        when(userRepository.findAll()).thenReturn(Arrays.asList(new User(ObjectId.get(), USER_NAME, USER_EMAIL)));
        Assertions.assertEquals(1, service.getUsers().size());
    }

    @Test
    public void getUserById(){
        ObjectId id = ObjectId.get();
        when(userRepository.findById(id)).thenReturn(new User(id,USER_NAME,USER_EMAIL));
        Assertions.assertEquals(id,service.getUserById(id));
    }

    @Test
    public void createUser(){
        User userToBeCreated = new User(null, USER_NAME,USER_EMAIL);
        when(userRepository.save(userToBeCreated)).thenReturn(userToBeCreated);
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(null);
        User user = service.createUser(userToBeCreated);

        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(USER_NAME,user.getName());
    }

}
