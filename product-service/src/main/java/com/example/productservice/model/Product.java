package com.example.productservice.model;

public class Product {
    private Long id;
    private String name;
    private Double price; // preço original
    private Double convertedPrice; // preço convertido (fallback = -1)
    private String currency;
    private String environment; // para identificar cache ou fallback

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getConvertedPrice() { return convertedPrice; }
    public void setConvertedPrice(Double convertedPrice) { this.convertedPrice = convertedPrice; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
}