package com.springboot.RestAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.RestAPI.Model.User;
import com.springboot.RestAPI.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();
		return userList;
		
	}
	
	
}
