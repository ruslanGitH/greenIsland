package com.shop.service;

import com.shop.model.entity.Category;
import com.shop.model.entity.Product;
import com.shop.model.repository.ICollageRepo;
import com.shop.model.repository.IProductRepo;
import com.shop.model.repository.IShopInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainPageService {
    @Autowired
    private IProductRepo productRepo;
    @Autowired
    private IShopInfoRepo shopInfoRepo;
    @Autowired
    private ICollageRepo collageRepo;

    public ResponseEntity<?> getAllProductsList() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepo.findAll());
    }
    public ResponseEntity<?> getShopInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(shopInfoRepo.findAll());
    }

    public ResponseEntity<?> getCollage() {
        return ResponseEntity.status(HttpStatus.OK).body(collageRepo.findAll());
    }

    public ResponseEntity<?> pump() {
        Product product = new Product();
        product.setCategory(null);
        product.setName("Первый продукт");
        product.setPrice(1101.1);
        product.setFamily(null);
        product.setImage("https://arte1.ru/images/detailed/4/23608.jpg");
        product.setDescription("Описнаие перовго продукта");
        productRepo.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(productRepo.findAll());
    }
}
