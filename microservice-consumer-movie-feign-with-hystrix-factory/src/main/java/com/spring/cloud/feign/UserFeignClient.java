package com.spring.cloud.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.cloud.entity.User;
import com.spring.cloud.hystrix.UserFeignWithFactory;

import feign.hystrix.FallbackFactory;

/**
 * 这里要注意，fallback和fallbackFactory不能同时使用，不然会有冲突
 * @author Administrator
 *
 */
@FeignClient(name = "microservice-provider-user", /*fallback = UserFeignClient.HystrixClientFallback.class,*/ fallbackFactory = UserFeignClient.HystrixClientFallbackFactory.class)
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
	
	@Component
	static class HystrixClientFallbackFactory implements FallbackFactory<UserFeignClient> {

		static Logger logger = LoggerFactory.getLogger(HystrixClientFallback.class);
		
		@Override
		public UserFeignClient create(Throwable cause) {
			
			logger.info("fallback reason was : ", cause.getMessage());
			
			return new UserFeignWithFactory() {

				@Override
				public User findById(Long id) {
					User user = new User();
					user.setId(-1L);
					
					return user;
				}
				
			};
		}
		
	}
	
}
