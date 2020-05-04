package com.mujiejun.controller.member;

import com.mujiejun.util.JwtTokenUtils;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Membercontroller {

    @RequestMapping ("/login")
    public String login(HttpServletRequest request){

        //登录，校验密码，生成token
         String username = request.getParameter("username");
        //
        JwtTokenUtils.createToken(username,false);
        return JwtTokenUtils.createToken(username,false);
    }

}
