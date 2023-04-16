package org.zil.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BaseUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseUserApplication.class, args);
    }
}
