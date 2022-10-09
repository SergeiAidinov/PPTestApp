package ru.yandex.incoming34.PPTestApp.component;

import org.springframework.stereotype.Component;

import java.net.URI;



public class UserOrder {

    private double price;
    private String currency;
    private String method;
    private String intent;
    private String description;

    public UserOrder(double price, String currency, String method, String intent, String description) {
        this.price = price;
        this.currency = currency;
        this.method = method;
        this.intent = intent;
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMethod() {
        return method;
    }

    public String getIntent() {
        return intent;
    }

    public String getDescription() {
        return description;
    }
}
