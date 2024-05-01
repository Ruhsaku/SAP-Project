package com.racooncoding.perfumestore.checkout;

import com.racooncoding.perfumestore.response.Response;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@Log
@Controller
public class ChargeController {
    private final ChargeService paymentsService;

    @Autowired
    public ChargeController(ChargeService chargeService) {
        this.paymentsService = chargeService;
    }

    // TODO --> amount must be Total Price from all products combined
    // TODO --> Generate new order in the database
    // TODO --> Save new address in the database for order
    @PostMapping(path = "/charge")
    public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
        Response response = new Response("Successful charging!");
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);

        // Charge the payment
        Charge charge = paymentsService.charge(chargeRequest);

        // Extract payment information and add it to the model
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());

        System.out.println(response.getMessage());
        // Redirect to the result page
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        Response response = new Response("Charging failed. Please try again.");
        model.addAttribute("error", ex.getMessage());
        System.err.println(response.getMessage());
        return "result";
    }
}