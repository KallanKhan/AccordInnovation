package com.accordInnovations.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.accordInnovations.entity.Item;
import com.accordInnovations.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Items", description = "API for managing items")
public class AccordController {

	private static final Logger logger = LogManager.getLogger(AccordController.class);
    @Autowired
	private ItemService itemService;

	@Operation(summary = "Fetch all items")
    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        logger.info("Fetching all items");
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @Operation(summary = "Fetch item by ID")
    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        logger.info("Fetching item by ID: {}", id);
        Optional<Item> item = itemService.getItemById(id);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Search items by category")
    @GetMapping("/items/search")
    public ResponseEntity<Page<Item>> searchItemsByCategory(
            @RequestParam String categoryName, Pageable pageable) {
        logger.info("Searching items by category: {}", categoryName);
        Page<Item> items = itemService.searchItemsByCategory(categoryName, pageable);
        return ResponseEntity.ok(items);
    }

    @Operation(summary = "Add a new item")
    @PostMapping("/items")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        logger.info("Adding new item: {}", item);
        Item savedItem = itemService.addItem(item);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an item")
    @PutMapping("/items/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        logger.info("Updating item with ID: {}", id);
        Optional<Item> item = itemService.updateItem(id, updatedItem);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete an item")
    @DeleteMapping("/items/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        logger.info("Deleting item with ID: {}", id);
        boolean isDeleted = itemService.deleteItem(id);
        if (isDeleted) {
            return new ResponseEntity<>("Item deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Item not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Call external API")
    @GetMapping("/external")
    public ResponseEntity<String> callExternalApi() {
        logger.info("Calling external API");
        String response = itemService.callGoogle();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Fetch secure items")
    @GetMapping("/secure-items")
    public ResponseEntity<List<Item>> getSecureItems() {
        logger.info("Fetching secure items");
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }
}