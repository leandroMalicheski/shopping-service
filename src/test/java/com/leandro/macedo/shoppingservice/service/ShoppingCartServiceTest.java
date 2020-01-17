package com.leandro.macedo.shoppingservice.service;

import com.leandro.macedo.shoppingservice.models.Item;
import com.leandro.macedo.shoppingservice.models.ShoppingCart;
import com.leandro.macedo.shoppingservice.models.User;
import com.leandro.macedo.shoppingservice.repositories.ShoppingCartRepository;
import com.leandro.macedo.shoppingservice.service.impl.ShoppingCartServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShoppingCartServiceTest {

    @Mock
    ShoppingCartRepository repository;

    @InjectMocks
    ShoppingCartService service = new ShoppingCartServiceImpl();

    @Test

    public void addItem() {
        ObjectId id = ObjectId.get();
        ShoppingCart shoppingCartToAddItem = new ShoppingCart(id, any(User.class));
        when(repository.findById(id)).thenReturn(shoppingCartToAddItem);
        when(repository.save(shoppingCartToAddItem)).thenReturn(null);

        ShoppingCart shoppingCart = service.addItemToShoppingCart(id, any(Item.class));
        Assertions.assertNotNull(shoppingCart.getItemsList());
    }

}
