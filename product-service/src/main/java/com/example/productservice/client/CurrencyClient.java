package com.example.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.productservice.model.Product;
import com.example.productservice.fallback.CurrencyClientFallback;

@FeignClient(name = "currency-service", url = "http://localhost:8082", fallback = CurrencyClientFallback.class)
public interface CurrencyClient {

    @GetMapping("/currency-exchange")
    Product getConvertedPrice(@RequestParam("currency") String currency);
}