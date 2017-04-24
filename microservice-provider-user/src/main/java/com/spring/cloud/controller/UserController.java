package com.spring.cloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.spring.cloud.entity.User;
import com.spring.cloud.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRep;
	@Autowired
	private EurekaClient eurekaClient;
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/simple/{id}")
	public User findById(@PathVariable Long id){
		return userRep.findOne(id);
	}
	
	@GetMapping("/eureka-instance")
	public String serviceUrl() {
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
		return instance.getHomePageUrl();
	}
	
	@GetMapping("/instance-info")
	public ServiceInstance showInfo(){
		ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}
	
	@PostMapping("/user")
	public User postUser(@RequestBody User user){
		return user;
	}
	
	@GetMapping("/get-user")
	public User getUser(User user){
		return user;
	}
	
	@GetMapping("list-all")
	public List<User> getUserList(){
		List<User> result = new ArrayList<User>();
		User user = new User(1, "Jackson");
		result.add(user);
		result.add(new User(2, "Micheal"));
		result.add(new User(3, "Ticket"));
		result.add(new User(4, "Tiffly"));
		return result;
	}
}
