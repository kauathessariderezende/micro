package com.example.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;

@RestController
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/product/{idProduct}/{targetCurrency}")
    public ResponseEntity<Product> getProduct(
        @PathVariable Long idProduct,
        @PathVariable String targetCurrency) {

        Product product = service.getProduct(idProduct, targetCurrency);
        if (product == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(product);
    }
}