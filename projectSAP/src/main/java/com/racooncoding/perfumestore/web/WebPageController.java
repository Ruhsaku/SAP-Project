package com.racooncoding.perfumestore.web;

import com.racooncoding.perfumestore.checkout.ChargeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}
