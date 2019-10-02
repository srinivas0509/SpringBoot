package com.springboot.RestAPI.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.RestAPI.Model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	@Query(value = "{call sp_findAllItems}", nativeQuery = true )
	public List<Item> findAllItems();
	
	@Query(value = "select * from Item where item_status='cancelled'",nativeQuery = true)
	public List<Item> findByItemStatus();
	
}
