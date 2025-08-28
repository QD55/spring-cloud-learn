package com.example.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderServiceConfig {


//    @Bean
    Retryer retryer() {
        return new Retryer.Default();
    }

    /**
     * 配合yml中:  logging:
     *                  level:
     *                  com.example.order.feign: debug
     *                  开启详细日志打印
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel () {
        return Logger.Level.FULL;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
