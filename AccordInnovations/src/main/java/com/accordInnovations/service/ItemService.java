package com.accordInnovations.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.accordInnovations.entity.Item;

public interface ItemService {

	 public abstract List<Item> getAllItems();

	    public abstract Optional<Item> getItemById(Long id);

	    public abstract Page<Item> searchItemsByCategory(String categoryName, Pageable pageable);

	    public abstract Item addItem(Item item);

	    public abstract Optional<Item> updateItem(Long id, Item updatedItem);

	    public abstract boolean deleteItem(Long id);

	    public abstract String callGoogle();
}
