package com.example.demo.Web;

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
    @RequestMapping("/employeeHome")
    public String employeeHome(){
        return "employeeHome";
    }
    @RequestMapping("/")
    public String login(){
        return "index";
    }
}
