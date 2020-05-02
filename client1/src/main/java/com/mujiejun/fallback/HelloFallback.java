package com.mujiejun.fallback;

import com.mujiejun.api.Hello;
import org.springframework.stereotype.Component;

@Component
public class HelloFallback implements Hello{
    @Override
    public String hello() {
        return "hello";
    }
}
