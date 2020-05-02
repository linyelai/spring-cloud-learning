package com.mujiejun.api;

import com.mujiejun.fallback.HelloFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="provider", fallback= HelloFallback.class)
public interface Hello {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello();
}
