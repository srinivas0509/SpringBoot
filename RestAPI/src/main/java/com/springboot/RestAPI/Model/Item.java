package com.springboot.RestAPI.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "item_id")
	private int item_id;
	
	@Column( name = "item_name")
	private String item_name;
	@Column( name = "quantity")
	private int quantity;
	
	@Column( name = "item_status")
	private String item_status;
	
	@Column (name = "seller_itemid")
	private int seller_itemid;
	
//	@Column( name = "order_id")
//	private int order_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id" ,insertable = true)
	@JsonIgnore
	private Orders orders;
	
		

	public int getItem_id() {
		return item_id;
	}


	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItem_status() {
		return item_status;
	}

	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}

	
	public int getSeller_itemid() {
		return seller_itemid;
	}


	public void setSeller_itemid(int seller_itemid) {
		this.seller_itemid = seller_itemid;
	}
	
	

//	public int getOrder_id() {
//		return order_id;
//	}
//
//	public void setOrder_id(int order_id) {
//		this.order_id = order_id;
//	}

	public Orders getOrders() {
		return orders;
	}


	public void setOrders(Orders orders) {
		this.orders = orders;
	}


	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_name=" + item_name + ", quantity=" + quantity + ", item_status="
				+ item_status  + ", seller_itemid=" + seller_itemid + "]";
	}


	
}
