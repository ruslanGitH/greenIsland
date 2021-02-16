package com.shop.service;

import com.shop.model.entity.*;
import com.shop.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminPageService {
    @Autowired
    private IProductRepo productRepo;
    @Autowired
    private ICategoryRepo categoryRepo;
    @Autowired
    private IFamilyRepo familyRepo;
    @Autowired
    private IShopInfoRepo shopInfoRepo;
    @Autowired
    private ICollageRepo collageRepo;

    public ResponseEntity<?> getCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepo.findAll());
    }

    public ResponseEntity<?> getFamily() {
        return ResponseEntity.status(HttpStatus.OK).body(familyRepo.findAll());
    }

    public ResponseEntity<?> saveProduct(Product product) {
        productRepo.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<?> saveCategory(Category category) {
        categoryRepo.save(category);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<?> saveFamily(Family family) {
        familyRepo.save(family);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<?> saveShopInfo(ShopInfo shopInfo) {
        shopInfoRepo.save(shopInfo);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<?> saveCollage(Collage collage) {
        collageRepo.save(collage);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<?> deleteProduct(Long productId) {
        productRepo.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<?> deleteCategory(Long id) {
        categoryRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<?> deleteFamily(Long id) {
        familyRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<?> updateProduct(Product product) {
        familyRepo.save(product.getFamily());
        categoryRepo.save(product.getCategory());
        productRepo.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(null);

    }
}
