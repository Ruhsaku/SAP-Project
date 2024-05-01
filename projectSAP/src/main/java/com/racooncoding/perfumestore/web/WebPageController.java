package com.racooncoding.perfumestore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPageController {
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/cart")
    public String cart() {
        return "cart";
    }

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping("/login")
    public String login() {
        return "index";
    }

    @RequestMapping("/")
    public String startServer() {
        return "index";
    }

    @RequestMapping("/login/employee")
    public String loginEmployee() {
        return "loginEmployee";
    }

    @RequestMapping("/dashboard/getAllProducts")
    public String dashboardGetAllProducts() {
        return "dashboard";
    }

    @RequestMapping("/dashboard/addProduct")
    public String dashboardAddProduct() {
        return "dashboard";
    }

    @RequestMapping("/dashboard/removeProduct")
    public String dashboardRemoveProduct() {
        return "dashboard";
    }

    @RequestMapping("/dashboard/updateProduct")
    public String dashboardUpdateProduct() {
        return "dashboard";
    }

    @RequestMapping("/charge")
    public String result() {
        return "result";
    }
}
