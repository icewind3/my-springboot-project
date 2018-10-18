package org.icewind.springcloudconsumer.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ye Jianyu
 * @date 2018/7/30
 */
@Configuration
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"org.icewind.springcloudconsumer.service"})
public class SpringCloudClientConfig {

    @Bean(name = "springCloudRestTemplate")
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
