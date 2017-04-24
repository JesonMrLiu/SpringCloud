package com.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
@ExcludeFromComponentScan
public class TestConfiguration {
	
//	@Autowired
//	IClientConfig config;
	
	/**
	 * 这里是设置Ribbon遵循的负载均衡的规则
	 * @return
	 */
	@Bean
	public IRule ribbonRule(/*IClientConfig config*/) {
		System.out.println("----->>ribbonRule in--->>");
		return new RandomRule();
	}
}
