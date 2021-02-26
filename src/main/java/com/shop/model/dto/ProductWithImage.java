package com.shop.model.dto;

import com.shop.model.entity.Category;
import com.shop.model.entity.Family;
import com.shop.model.entity.Product;
import lombok.Data;

import javax.persistence.*;
@Data
public class ProductWithImage {
    private Long id;
    private String name;
    private double price;
    private byte[] image;
    private String description;
    private Category category;
    private Family family;
    private boolean active;
    public ProductWithImage(Product product, byte[] image) {
        this.id =product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.image = image;
        this.description =product.getDescription();
        this.category = product.getCategory();
        this.family = product.getFamily();
        this.active = product.isActive();
    }


}
