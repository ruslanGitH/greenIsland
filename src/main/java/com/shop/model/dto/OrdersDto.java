package com.shop.model.dto;

import lombok.Data;

@Data
public class OrdersDto {
    private int count;
    private ProductDtoForSend product;

    public OrdersDto(int count, ProductDtoForSend product) {
        this.count = count;
        this.product = product;
    }
}