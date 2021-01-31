package com.shop.controllers;

import com.shop.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainControllers {
    @Autowired
    private MainPageService mainPageService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        return mainPageService.getAllProductsList();
    }

    @GetMapping("/shopInfo")
    public ResponseEntity<?> getShopInfo(){
        return mainPageService.getShopInfo();
    }
    @GetMapping("/collage")
    public ResponseEntity<?> getCollage(){
        return mainPageService.getCollage();
    }
    @GetMapping("/pump")
    public ResponseEntity<?> pump(){
        return mainPageService.pump();
    }


}
