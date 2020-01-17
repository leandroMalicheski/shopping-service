package com.leandro.macedo.shoppingservice.models;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {
    public ShoppingCart currentCart;
    private static final int ITEM_QTY = 1;
    private static final int ITEM_VALUE = 1;
    private static final String ITEM_NAME = "mouse";
    private static final String ITEM_ADAPTER = "adapter";


    @BeforeEach
    public void  provideAShoppingCart(){
        currentCart = new ShoppingCart(ObjectId.get(), new User(ObjectId.get(),"test","email"));
        currentCart.addItem(new Item(ObjectId.get(), ITEM_QTY, 1.99, ITEM_NAME));
    }

    @Test
    public void closePurchaseMustReturnItemsTotalValue(){
        currentCart.addItem(new Item(ObjectId.get(), ITEM_QTY, ITEM_VALUE, ITEM_NAME));
        currentCart.closePurchase();
        Assertions.assertEquals(2.99,currentCart.getItemsTotalValue());
    }

    @Test
    public void closePurchaseMustConsiderItemQty(){
        Item itemToBeIncreased = new Item(ObjectId.get(), ITEM_QTY,ITEM_VALUE, ITEM_NAME );
        currentCart.addItem(itemToBeIncreased);
        currentCart.addItem(itemToBeIncreased);
        currentCart.closePurchase();
        Assertions.assertEquals(3.99,currentCart.getItemsTotalValue());
    }

    @Test
    public void closePurchaseMustBeSortedByItemName(){
        currentCart.addItem(new Item(ObjectId.get(), ITEM_QTY, ITEM_VALUE, ITEM_ADAPTER));
        currentCart.closePurchase();
        Assertions.assertEquals(ITEM_ADAPTER,currentCart.getItemsList().get(0).getName());
        Assertions.assertEquals(ITEM_NAME,currentCart.getItemsList().get(1).getName());
    }

    @Test
    public void cartMustAddItemListForFirstItem(){
        Assertions.assertEquals(1,currentCart.getItemsList().size());
    }

    @Test
    public void cartMustAddItemToTheList(){
        currentCart.addItem(new Item(ObjectId.get(), ITEM_QTY, ITEM_VALUE, ITEM_NAME));
        Assertions.assertEquals(1,currentCart.getItemsList().size());
    }

    @Test
    public void cartMustIncreaseQtyForAnItem(){
        Item itemToBeIncreased = new Item(ObjectId.get(), ITEM_QTY,ITEM_VALUE, ITEM_NAME);
        currentCart.addItem(itemToBeIncreased);
        currentCart.addItem(itemToBeIncreased);
        Assertions.assertEquals(2,currentCart.getItemsList().size());
        Assertions.assertEquals(2,itemToBeIncreased.getQty());
    }

    @Test
    public void cartMustDecreaseQtyForAItem(){
        Item itemToBeDecreased = new Item(ObjectId.get(), ITEM_QTY, ITEM_VALUE, ITEM_NAME);
        currentCart.addItem(itemToBeDecreased);
        currentCart.addItem(itemToBeDecreased);
        currentCart.removeItem(itemToBeDecreased);
        Assertions.assertEquals(2, currentCart.getItemsList().size());
        Assertions.assertEquals(1, itemToBeDecreased.getQty());
    }

    @Test
    public void cartMustRemoveItemWith1QtyFromList(){
        Item itemToBeRemoved = new Item(ObjectId.get(), ITEM_QTY,ITEM_VALUE,ITEM_NAME);
        currentCart.addItem(itemToBeRemoved);
        currentCart.removeItem(itemToBeRemoved);
        Assertions.assertEquals(1,currentCart.getItemsList().size());
    }


}
