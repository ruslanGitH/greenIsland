package com.shop.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private MultipartFile image;
    private String description;
    private Object category;
    private Object family;
    private boolean active;
}
