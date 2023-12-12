package com.example.playWorld.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String root(){
        return "/login/home";
    }

    @GetMapping("/home")
    public String home(){
        return "/login/home";
    }

    @GetMapping("/hello")
    public String hello(){
        return "/login/hello";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "/login/login";
    }

}
