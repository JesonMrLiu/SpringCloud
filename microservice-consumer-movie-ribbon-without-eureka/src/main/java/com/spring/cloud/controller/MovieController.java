package com.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.cloud.entity.User;

@RestController
public class MovieController {

	@Autowired
	private RestTemplate restTmp;
	@Autowired
	private LoadBalancerClient loadBalanceClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id){
		
		ServiceInstance instance = loadBalanceClient.choose("microservice-provider-user");
		System.out.println(instance.getServiceId()+ "-----" + instance.getPort());
		
		//这里的microservice-provider-user为VIP，在Spring Cloud中VIP是指Virsual IP，即虚拟IP
		return restTmp.getForObject("http://microservice-provider-user/simple/" + id, User.class);
	}
	
	@GetMapping("/test")
	public String test(){
		ServiceInstance instance = loadBalanceClient.choose("microservice-provider-user");
		System.out.println("111-->" + instance.getServiceId()+ ":" + instance.getPort());
		
		ServiceInstance instance2 = loadBalanceClient.choose("microservice-provider-user2");
		System.out.println("222-->" + instance2.getServiceId()+ ":" + instance2.getPort());
		return "1";
	}
}
