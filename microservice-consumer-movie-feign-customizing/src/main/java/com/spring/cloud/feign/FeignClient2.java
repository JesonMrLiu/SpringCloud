package com.spring.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.spring.cloud.config.BeanConfiguration;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "xxx", url = "http://localhost:8761/", configuration = BeanConfiguration.class)
public interface FeignClient2 {

//	@RequestMapping(value = "/eureka/apps/{serviceName}")
	@RequestLine("GET /eureka/apps/{serviceName}")
	public String findServiceInfoEurekaByServieName(@Param("serviceName") String serviceName);
	
}
