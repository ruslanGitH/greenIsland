package com.shop.model.dto;

import lombok.Data;

@Data
public class OrdersDto {
    private int count;
    private ProductDto product;

    public OrdersDto(int count, ProductDto product) {
        this.count = count;
        this.product = product;
    }
}