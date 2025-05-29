package com.example.productservice.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.productservice.client.CurrencyClient;
import com.example.productservice.model.Product;

@Service
public class ProductService {

    private final CurrencyClient currencyClient;

    private static Map<Long, Product> productDB = new HashMap<>();

    static {
        Product p = new Product();
        p.setId(1L);
        p.setName("Produto A");
        p.setPrice(100.0);
        p.setCurrency("USD");
        productDB.put(p.getId(), p);
    }

    public ProductService(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Cacheable(value = "productsCache", key = "#idProduct + '-' + #targetCurrency")
    public Product getProduct(Long idProduct, String targetCurrency) {
        Product product = productDB.get(idProduct);
        if (product == null) return null;

        Product conversion = currencyClient.getConvertedPrice(targetCurrency);

        double convertedRate = conversion.getConvertedPrice() != null && conversion.getConvertedPrice() >= 0
                ? conversion.getConvertedPrice()
                : -1;

        Product response = new Product();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setCurrency(product.getCurrency());
        response.setConvertedPrice(convertedRate == -1 ? -1 : product.getPrice() * convertedRate);
        response.setEnvironment(convertedRate == -1 ? "original" : "converted & cached");

        return response;
    }
}