package com.springboot.RestAPI.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.RestAPI.Model.SellerItem;
import com.springboot.RestAPI.Repository.SellerItemRepository;
import com.springboot.RestAPI.service.InventoryService;

@RestController
public class InventoryController {
	
	@Autowired
	private SellerItemRepository sellerItemrepo;
	
	@Autowired
	private InventoryService inventoryservice;
	
	
	@GetMapping("/sellerItems")
	public List<SellerItem> allItemList() {
		List<SellerItem> itemlist = sellerItemrepo.findAll();
		return itemlist;
	}
	
	@GetMapping("/sellerItems/{id}")
	public Optional<SellerItem> getItemById(@PathVariable int id) {
		 Optional<SellerItem> itemsById = inventoryservice.getItemsById(id);
		return itemsById;
	}
	
	@PostMapping("/sellerItems")
	public ResponseEntity<Object> addSellerItem(@RequestBody SellerItem item) {
		SellerItem saveditem = sellerItemrepo.save(item);
			
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveditem.getSellerItemID()).toUri();
		
		return ResponseEntity.created(location).build();
				
	}
	
	@DeleteMapping("/sellerItems/{item_id}")
	public void removeItem(@PathVariable int item_id ) {
		sellerItemrepo.deleteById(item_id);
	}
	
//	@PutMapping("/update}")
//	public SellerItem updateQunatity(@RequestBody SellerItem item) {
//		try {
//			SellerItem seller= sellerItemrepo.updateQuantity(item.getSellerItemID(), item.getAvailableQuantity());
//			return seller;
//
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	@PutMapping("/selleritems/{id}/{quantity}")
	public int updateQuantity(@PathVariable("id") int id, @PathVariable("quantity") int quantity) {
		
		int updateduser = inventoryservice.updateQuantity(id, quantity);
		
		return updateduser;
		
	}
	
	
}
