package com.leandro.macedo.shoppingservice.controller;

import com.leandro.macedo.shoppingservice.models.Item;
import com.leandro.macedo.shoppingservice.service.ItemService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private ItemService service;

    @Test
    public void getItems() throws Exception {
        when(service.getItems()).thenReturn(Arrays.asList(new Item(ObjectId.get(),1, 2.99, "mouse"), new Item(ObjectId.get(),1, 2.99, "mouse2")));
        ResponseEntity<List> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/item").toString(), List.class);

        Assumptions.assumeTrue((response.getStatusCode().equals(HttpStatus.OK)));
    }
}
