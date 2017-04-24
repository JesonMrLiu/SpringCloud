package com.spring.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import com.spring.cloud.config.BeanConfiguration;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "xxx", url = "http://localhost:8761/", configuration = BeanConfiguration.class, fallback = FeignClient2.FeignClient2Fallback.class)
public interface FeignClient2 {

//	@RequestMapping(value = "/eureka/apps/{serviceName}")
	@RequestLine("GET /eureka/apps/{serviceName}")
	public String findServiceInfoEurekaByServieName(@Param("serviceName") String serviceName);
	
	@Component
	static class FeignClient2Fallback implements FeignClient2 {

		@Override
		public String findServiceInfoEurekaByServieName(String serviceName) {
			System.out.println("hello...");
			return "{\"info\":\"Nothing...\"}";
		}
		
	}
	
}
