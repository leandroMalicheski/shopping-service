package com.leandro.macedo.shoppingservice.service;

import com.leandro.macedo.shoppingservice.models.Item;
import com.leandro.macedo.shoppingservice.repositories.ItemRepository;
import com.leandro.macedo.shoppingservice.service.impl.ItemServiceImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ItemServiceTest {

    private static final int ITEM_QTY = 1;
    private static final double ITEM_VALUE = 2.99;
    private static final String ITEM_NAME = "mouse";

    @Mock
    private ItemRepository repository;

    @InjectMocks
    private ItemService service = new ItemServiceImpl();

    @Test
    public void getItemsFromServiceTest() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Item(ObjectId.get(), ITEM_QTY, ITEM_VALUE, ITEM_NAME), new Item(ObjectId.get(), ITEM_QTY, ITEM_VALUE, ITEM_NAME)));
        Assertions.assertEquals(2, service.getItems().size());
    }

    @Test
    public void getItemById(){
        ObjectId id = ObjectId.get();
        when(repository.findById(id)).thenReturn(new Item(id , ITEM_QTY, ITEM_VALUE, ITEM_NAME));
        Assertions.assertEquals(id, service.getItemById(id).getId());
    }

    @Test
    public void createItem(){
        Item itemToBeSaved = new Item(null , ITEM_QTY, ITEM_VALUE, ITEM_NAME);
        when(repository.save(itemToBeSaved)).thenReturn(itemToBeSaved);
        Item item = service.createItem(itemToBeSaved);

        Assertions.assertEquals(ITEM_NAME,item.getName());
        Assertions.assertNotNull(item.getId());
    }

}
