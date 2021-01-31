package com.shop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ShopInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String firstLevelText;
    private String secondLevelText;
    private String img;

}
