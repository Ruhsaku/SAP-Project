package com.racooncoding.perfumestore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
    @RequestMapping("/login")
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
