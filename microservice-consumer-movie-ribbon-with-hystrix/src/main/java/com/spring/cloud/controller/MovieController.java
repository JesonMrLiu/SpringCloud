package com.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.cloud.entity.User;

@RestController
public class MovieController {

	@Autowired
	private RestTemplate restTmp;
	
	/**
	 * Hystrix 的默认超时时间为1秒，当1秒都没有相应这个http://microservice-provider-user/simple/ 请求时，
	 * 就会任务请求不到结果，从而直接掉回调方法。
	 * @param id
	 * @return
	 */
	@GetMapping("/movie/{id}")
	@HystrixCommand(fallbackMethod = "findByIdCallback")
	public User findById(@PathVariable Long id){
		//这里的microservice-provider-user为VIP，在Spring Cloud中VIP是指Virsual IP，即虚拟IP
		return restTmp.getForObject("http://microservice-provider-user/simple/" + id, User.class);
	}
	
	/**
	 * 这个回调方法中，方法名称与返回值必须要跟原方法一致
	 * @param id
	 * @return
	 */
	public User findByIdCallback(Long id){
		User user = new User();
		user.setId(0L);
		return user;
	}
	
}
