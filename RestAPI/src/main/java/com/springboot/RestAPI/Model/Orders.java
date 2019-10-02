package com.springboot.RestAPI.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table (name = "orders" ,schema= "orderdb")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "order_id")
	private int order_id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date order_date = new Date();
	private String user_placed;
	

	@OneToMany(mappedBy="orders")
//	@JsonIgnore
	private List<Item> items;
	

	public Orders() {
		
	}


	


	public Orders(Date order_date, String user_placed) {
	
		this.order_date = order_date;
		this.user_placed = user_placed;
	}
	

	

	public int getOrder_id() {
		return order_id;
	}


	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public String getUser_placed() {
		return user_placed;
	}

	public void setUser_placed(String user_placed) {
		this.user_placed = user_placed;
	}


	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}




	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", order_date=" + order_date + ", user_placed=" + user_placed
				+ ", items=" + items + "]";
	}
	

}
