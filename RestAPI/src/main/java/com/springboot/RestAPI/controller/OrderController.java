package com.springboot.RestAPI.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.RestAPI.Model.Item;
import com.springboot.RestAPI.Model.Orders;
import com.springboot.RestAPI.Model.Post;
import com.springboot.RestAPI.Model.SellerItem;
import com.springboot.RestAPI.Repository.ItemRepository;
import com.springboot.RestAPI.Repository.OrderRepository;
import com.springboot.RestAPI.Repository.PostRepository;
import com.springboot.RestAPI.service.InventoryService;
import com.springboot.RestAPI.service.ItemService;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository orderrepo;
	
	@Autowired
	private ItemRepository itemrepo;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/orders")
	public List<Orders> getAllOrders() {
		
		List<Orders> orderlist = orderrepo.findAll();
		System.out.println(orderlist);
		return orderlist;
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Object> addOrder(@RequestBody Orders order) {
		
			int seller_itemid;
			int quantity;
			System.out.println("Before insert :" + order);
//			System.out.println(order.getItems());
			//List<Item> items = order.getItems();
			//System.out.println(items.size());
			//System.out.println("Item before :"+ items);
			 Orders savedOrder= orderrepo.save(order);
//			 Orders savedOrder=order;
			System.out.println(savedOrder.getOrder_id());
			System.out.println(savedOrder.getItems());
			for(Item tempItem:savedOrder.getItems()) {
				Item itement=new Item();
				itement.setItem_name(tempItem.getItem_name());
//				itement.setItem_status(tempItem.getItem_status());
				itement.setQuantity(tempItem.getQuantity());
				itement.setSeller_itemid(tempItem.getSeller_itemid());
				System.out.println(savedOrder);
				itement.setOrders(savedOrder);
//				itement.setOrder_id(savedOrder.getOrder_id());
				
				int id= itement.getSeller_itemid();
				Optional<SellerItem> itemsById = inventoryService.getItemsById(id);
				int availableQuantity = itemsById.get().getAvailableQuantity();
				if(tempItem.getQuantity() - availableQuantity >0)
					itement.setItem_status("cancelled");
				else {
					itement.setItem_status("placed");
					seller_itemid = tempItem.getSeller_itemid();
					quantity = tempItem.getQuantity();
					inventoryService.reduceQunatity(seller_itemid, quantity);
				}
				
				itemrepo.save(itement);
				
				
			}
			
		System.out.println("After insert :" + savedOrder);
		
		
			
//			List<Item> item = savedOrder.getItems();
//			for (Item n:item) {
//				seller_itemid = n.getSeller_itemid();
//				quantity = n.getQuantity();
//				inventoryService.reduceQunatity(seller_itemid, quantity);
//			}
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedOrder.getOrder_id()).toUri();
			
			return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/orders/{order_id}")
	public Optional<Orders> getOrderById (@PathVariable int order_id) {
		
		Optional<Orders> resultorder = orderrepo.findById(order_id);
		return resultorder;
	}
	
	@GetMapping("/orders/{order_id}/items")
	public List<Item> getItemsByOrderId(@PathVariable int order_id ) {
		Optional<Orders> foundOrder = orderrepo.findById(order_id);
		Orders order = foundOrder.get();
		return order.getItems();
	}
	
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return itemrepo.findAllItems();
	}
	
	@GetMapping("/cancelledItems")
	public List<Item> getAllCancelledItems() {
		
		List<Item> allCancelledItems = itemService.getAllCancelledItems();
		
		return allCancelledItems;
	}
	
}

