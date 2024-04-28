package com.racooncoding.perfumestore.checkout;

import com.racooncoding.perfumestore.response.Response;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log
@Controller
public class ChargeController {
    private final ChargeService paymentsService;

    @Autowired
    public ChargeController(ChargeService chargeService) {
        this.paymentsService = chargeService;
    }

    @PostMapping("/checkout")
    // TODO --> Proper mapping to result.html
    public ResponseEntity<Response> charge(@RequestBody ChargeRequest chargeRequest, Model model)
            throws StripeException {
        Response response = new Response("Successful charging!", "/result");
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        System.out.println(response.getMessage());
        return ResponseEntity.ok().body(response);
    }

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<Response> handleError(Model model, StripeException ex) {
        Response response = new Response("Charging failed. Please try again.");
        model.addAttribute("error", ex.getMessage());
        System.err.println(response.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}