package com.racooncoding.perfumestore.checkout;

import lombok.Data;

@Data
public class ChargeRequest {
    public enum Currency {
        EUR, USD;
    }
    private String description;
    private Integer amount; // cents
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;

    public void setDescription(String description) {
        this.description = description;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}