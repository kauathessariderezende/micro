package com.example.productservice.fallback;

import org.springframework.stereotype.Component;

import com.example.productservice.client.CurrencyClient;
import com.example.productservice.model.Product;

@Component
public class CurrencyClientFallback implements CurrencyClient {

    @Override
    public Product getConvertedPrice(String currency) {
        Product fallback = new Product();
        fallback.setConvertedPrice(-1.0);
        fallback.setEnvironment("fallback");
        return fallback;
    }
}