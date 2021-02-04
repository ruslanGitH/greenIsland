package com.shop.controllers;

import com.shop.model.entity.Product;
import com.shop.service.AdminPageService;
import com.shop.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private AdminPageService adminPageService;
    @Autowired
    private MainPageService mainPageService;

    @RequestMapping(value = "admin/product", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        return adminPageService.saveProduct(product);
    }
    @RequestMapping(value = "admin/product/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeProduct(@PathVariable("productId")Long productId) {
        return adminPageService.deleteProduct(productId);
    }
    @RequestMapping(value = "admin/product", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return adminPageService.updateProduct(product);
    }
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        return mainPageService.getAllProductsList();
    }
}
