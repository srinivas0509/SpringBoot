package com.springboot.RestAPI.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "selleritem")
public class SellerItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sellerItemID;
	private String itemName;
//	@Column(name = "AvailableQuantity")
	private int availableQuantity;

	protected SellerItem() {
		
	}

	public SellerItem(String itemName, int availableQuantity) {
		super();
		this.itemName = itemName;
		this.availableQuantity = availableQuantity;
	}

	public int getSellerItemID() {
		return sellerItemID;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	@Override
	public String toString() {
		return "SellerItem [sellerItemID=" + sellerItemID + ", itemName=" + itemName + ", availableQuantity="
				+ availableQuantity + "]";
	}

	
	
	
}
