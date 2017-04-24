package com.spring.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;

@Configuration
public class FooConfiguration {
	
	@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

	@Bean
	Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
	
}
