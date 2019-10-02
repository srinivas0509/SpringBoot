package com.springboot.RestAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.RestAPI.Model.Item;
import com.springboot.RestAPI.Repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepo;

	public List<Item> getAllCancelledItems () {
		List<Item> canItems = itemRepo.findByItemStatus();
		return canItems;
	}
}
