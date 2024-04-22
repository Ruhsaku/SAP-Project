package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// This controller name is ambiguous.
// Rename to something that will better describe what this class is supposed to do.
public class WebController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/cart")
    public String cart(){
        return "cart";
    }
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
    @RequestMapping("/")
    public String login(){
        return "index";
    }

    @RequestMapping("/login/employee")
    public String loginEmployee(){
        return "loginEmployee";
    }
    @RequestMapping("/dashboard/getAllProducts")
    public String dashboardGetAllProducts(){
        return "dashboard";
    }
}
