package com.mujiejun;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableAutoConfiguration
@RefreshScope
@MapperScan("com.mujiejun.mapper")
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }


    @Value("${user.username}")
    private String username;
    @Autowired
    private StoreIntegration integration;
    @RequestMapping("/hello")
    public String home() {

        return integration.getStores(null)+"\tusername:"+username;   }

    @Component
    public class StoreIntegration {

        @HystrixCommand(fallbackMethod = "defaultStores")
        public Object getStores(Map<String, Object> parameters)throws  NullPointerException {
            //do stuff that might fail
            if(parameters==null)
            {
                throw new NullPointerException();
            }
            return "hello";
        }

        public Object defaultStores(Map<String, Object> parameters) {
            return "hello world.....";
        }
    }



}