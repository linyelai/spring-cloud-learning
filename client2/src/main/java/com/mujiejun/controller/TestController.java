package com.mujiejun.controller;

import com.mujiejun.api.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    @Autowired
    private Hello hello;
    @RequestMapping("/user")
    public String sayHello()
    {
        String result = hello.hello();
        return "client2"+result;
    }
}
