package com.springboot.RestAPI.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
//@JsonFilter("UserFilter")
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min=3, message="name should have minimum 3 characters")
	private String name;
	
	@Past
	private Date birthDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;

	protected User() {

	}

	public User(String name, Date birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}
	
	

	public User(@Size(min = 3, message = "name should have minimum 3 characters") String name, @Past Date birthDate,
			List<Post> posts) {
		super();
		this.name = name;
		this.birthDate = birthDate;
		this.posts = posts;
	}

	public Integer getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}

}
