package com.shop.service;

import com.shop.model.repository.ICollageRepo;
import com.shop.model.repository.IProductRepo;
import com.shop.model.repository.IShopInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
