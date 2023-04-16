package org.zil.event;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EventConfig {

    @Bean
    @LoadBalanced
    public RestTemplate rest() {
        //This rest template bean will be anle choose between one or more instance of the target url
        return new RestTemplate();
    }
}
