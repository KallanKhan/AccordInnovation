package com.accordInnovations.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accordInnovations.entity.Item;
import com.accordInnovations.repository.ItemRepository;
import com.accordInnovations.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	private static final Logger logger = LogManager.getLogger(ItemService.class);

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	RestTemplate restTemplate;
	public List<Item> getAllItems() {
		logger.info("Fetching all items from database");
		return itemRepository.findAll();
	}

	public Optional<Item> getItemById(Long id) {
		logger.info("Fetching item by ID: {}", id);
		return itemRepository.findById(id);
	}

	public Page<Item> searchItemsByCategory(String categoryName, Pageable pageable) {
		logger.info("Searching items by category: {}", categoryName);
		return itemRepository.findItemsByCategoryName(categoryName, pageable);
	}

	public Item addItem(Item item) {
		logger.info("Saving new item: {}", item);
		return itemRepository.save(item);
	}

	public Optional<Item> updateItem(Long id, Item updatedItem) {
		logger.info("Updating item with ID: {}", id);
		return itemRepository.findById(id).map(existingItem -> {
			existingItem.setName(updatedItem.getName());
			existingItem.setCategory(updatedItem.getCategory());
			return itemRepository.save(existingItem);
		});
	}

	public boolean deleteItem(Long id) {
		logger.info("Deleting item with ID: {}", id);
		if (itemRepository.existsById(id)) {
			itemRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public String callGoogle() {
		logger.info("Calling Google external API");
		
		return restTemplate.getForObject("https://www.google.com", String.class);
	}
}

