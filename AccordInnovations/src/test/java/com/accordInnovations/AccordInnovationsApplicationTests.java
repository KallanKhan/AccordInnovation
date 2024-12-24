package com.accordInnovations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.accordInnovations.controller.AccordController;
import com.accordInnovations.entity.Category;
import com.accordInnovations.entity.Item;
import com.accordInnovations.service.ItemService;

@SpringBootTest
class AccordInnovationsApplicationTests {

	@Mock
	private ItemService itemService;

    @InjectMocks
    private AccordController accordController;

    public AccordInnovationsApplicationTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllItems() {
    	List<Item> mockItems = new ArrayList<>();
        mockItems.add(new Item(1L, "Item1", new Category(1L, "Category1")));
        mockItems.add(new Item(2L, "Item2", new Category(2L, "Category2")));

        when(itemService.getAllItems()).thenReturn(mockItems);

        ResponseEntity<List<Item>> response = accordController.getAllItems();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());

    }

   
    @Test
    public void testGetItemById() {
        // Mock data
        Category mockCategory = new Category(1L, "Category1");
        Item mockItem = new Item(1L, "Item1", mockCategory);
        when(itemService.getItemById(1L)).thenReturn(Optional.of(mockItem));

        // Execute the test
        ResponseEntity<Item> response = accordController.getItemById(1L);

        // Assertions
        assertEquals(200, response.getStatusCodeValue()); // Assert 200 OK status
        assertNotNull(response.getBody()); // Ensure the response body is not null
        assertEquals("Item1", response.getBody().getName()); // Verify the item's name
    }


    @Test
    public void testAddItem() {
        Item mockItem = new Item(1L, "Item1", new Category(1L, "Category1"));
        when(itemService.addItem(any(Item.class))).thenReturn(mockItem);

        ResponseEntity<Item> response = accordController.addItem(mockItem);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Item1", response.getBody().getName());
    }

    @Test
    public void testDeleteItem() {
        when(itemService.deleteItem(1L)).thenReturn(true);

        ResponseEntity<String> response = accordController.deleteItem(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Item deleted successfully.", response.getBody());
    }

    @Test
    public void testGetItemByIdNotFound() {
        // Mock data
        when(itemService.getItemById(1L)).thenReturn(Optional.empty());

        // Execute the test
        ResponseEntity<Item> response = accordController.getItemById(1L);

        // Assertions
        assertEquals(404, response.getStatusCodeValue(), "Expected HTTP status 404 when item is not found.");
        assertNull(response.getBody(), "Response body should be null when item is not found.");
    }

}