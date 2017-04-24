package com.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cloud.entity.User;
import com.spring.cloud.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRep;
	
	@GetMapping("/simple/{id}")
	public User findById(@PathVariable Long id){
		return userRep.findOne(id);
	}
	
}
