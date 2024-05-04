package com.tradingcryptos.coinbaseapi.models;

public class CryptoPrice {
    private String amount;
    private String base;
    private String currency;

    // Constructors, getters, and setters

    public CryptoPrice() {}

    public CryptoPrice(String amount, String base, String currency) {
        this.amount = amount;
        this.base = base;
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

