package com.shop.model.dto;

import lombok.Data;

@Data
public class ProductDtoForSend {
    String name;
    double price;
    String description;
    String familyName;
    String categoryName;

    public ProductDtoForSend(String name, double price, String description, String familyName, String categoryName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.familyName = familyName;
        this.categoryName = categoryName;
    }
}
