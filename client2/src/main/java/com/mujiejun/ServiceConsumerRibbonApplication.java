package com.mujiejun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class ServiceConsumerRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerRibbonApplication.class, args);
    }

}