package com.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name="microservice-provider-user2", configuration = TestConfiguration.class)
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class))
public class ConsumerMovieRibbonApplication {

	/**
	 * 该方法以方法名称作为注解名称，
	 * <p><b style='color:red'>注意：这个方法的方法名与MovieController的RestTemplate的实例名称一样</b>
	 * 
	 * <p><p><code>@LoadBalanced</code>：包含了Ribbon，实现负载均衡
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTmp(){
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieRibbonApplication.class, args);
	}
	
}
