package com.springboot.RestAPI.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springboot.RestAPI.Model.Post;
import com.springboot.RestAPI.Model.User;
import com.springboot.RestAPI.Repository.PostRepository;
import com.springboot.RestAPI.Repository.UserRepository;
import com.springboot.RestAPI.exception.UserNotFoundException;
import com.springboot.RestAPI.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/usersold")
	public List<User> getAllUsers() {
		return userservice.getAllUsers();
	}
	
	/*
	 * @GetMapping("/users/filter") public MappingJacksonValue
	 * retrieveAllUsersfiltered() { List<User> userlist1 = userRepository.findAll();
	 * 
	 * SimpleBeanPropertyFilter filter =
	 * SimpleBeanPropertyFilter.filterOutAllExcept("id","name");
	 * 
	 * FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter",
	 * filter);
	 * 
	 * MappingJacksonValue mapping = new MappingJacksonValue(userlist1);
	 * 
	 * mapping.setFilters(filters); //System.out.println(userlist); return mapping;
	 * }
	 */
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		List<User> userlist = userRepository.findAll();
		System.out.println(userlist);
		return userlist;
	}

	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("id -" + id);
		
		Resource<User> resource = new Resource<User>(user.get());
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
	
		return resource;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		User saveduser = userRepository.save(user);
		
		URI location=ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(saveduser.getId()).toUri();
		
		return  ResponseEntity.created(location).build();
	}
	

	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
	
		userRepository.deleteById(id);
	}
	
	
	@GetMapping("/users/{userid}/posts")
	public List<Post> getpostsbyuserid(@PathVariable int userid) {
		Optional<User> user = userRepository.findById(userid);
		if(!user.isPresent())
			throw new UserNotFoundException("id-" + userid);
		return user.get().getPosts();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPostByUserId(@PathVariable int id, @RequestBody Post post) {
		Optional<User> optionalUser=userRepository.findById(id);
		
		if(!optionalUser.isPresent())
		throw new UserNotFoundException("id-"+ id);
		
		User user= optionalUser.get();
		
		post.setUser(user);
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(post.getId()).toUri();
	
		return ResponseEntity.created(location).build();
	}
}

	
