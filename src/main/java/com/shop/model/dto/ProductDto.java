package com.shop.model.dto;

import lombok.Data;

import java.sql.Blob;


@Data
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private byte[] image;
    private String description;
    private Object category;
    private Object family;
}
