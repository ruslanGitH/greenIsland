package com.shop.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Collage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mainImage;
    private String topLeftImage;
    private String bottomLeftImage;

}
