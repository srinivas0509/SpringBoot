package com.springboot.RestAPI.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.RestAPI.Model.SellerItem;

@Repository
public interface SellerItemRepository extends JpaRepository<SellerItem , Integer> {

	
	  @Modifying		  
	  @Query(value ="update SellerItem a set a.availableQuantity= :quantity where a.sellerItemID = :id")
	  @Transactional
	  int updateQuantity(@Param("id") int id, @Param("quantity") int quantity);
	 
}
