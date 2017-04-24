package com.spring.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.cloud.entity.User;

@FeignClient(name = "microservice-provider-user", fallback = UserFeignClient.HystrixClientFallback.class)
public interface UserFeignClient {

	/**
	 * 这里需要注意的两个地方
	 * <p>
	 * 1、在这里不支持GetMapping注解，如果用这个注解，不能启动
	 * <p>
	 * 2、@PathVariable需要设置value，如果不设置也不能成功启动
	 * 
	 * @param id	
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/simple/{id}")
	public User findById(@PathVariable("id") Long id);
	
	@Component
	static class HystrixClientFallback implements UserFeignClient {

		@Override
		public User findById(Long id) {
			System.out.println("----fallback----");
			User user = new User();
			user.setId(0L);
			return user;
		}
	    
	}
	
}
