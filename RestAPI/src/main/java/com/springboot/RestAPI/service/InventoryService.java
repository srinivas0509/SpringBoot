package com.springboot.RestAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.RestAPI.Model.Item;
import com.springboot.RestAPI.Model.SellerItem;
import com.springboot.RestAPI.Repository.SellerItemRepository;

@Service
public class InventoryService {

	@Autowired
	private SellerItemRepository sellerItemrepo;
	
	public int updateQuantity(int id,  int quantity) {
		
		int updateduser = sellerItemrepo.updateQuantity(id, quantity);
		
		return updateduser;
		
	}
	
	public int reduceQunatity(int item_id, int quantity_reduced) {
		
		Optional<SellerItem> item = sellerItemrepo.findById(item_id);
		
		int AvailableQuantity = item.get().getAvailableQuantity();
		
		int actual_available = AvailableQuantity - quantity_reduced;
		
		return updateQuantity(item_id, actual_available);
	}
	
	public List<SellerItem> getAllItemsInventory() {
		List<SellerItem> allitems = sellerItemrepo.findAll();
		return allitems;
	}
	
	public Optional<SellerItem> getItemsById(int id) {
		Optional<SellerItem> sellerItem = sellerItemrepo.findById(id);
		return sellerItem;
		
	}
	
	
		

}
