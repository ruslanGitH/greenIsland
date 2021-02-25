package com.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.model.dto.ProductDto;
import com.shop.model.entity.*;
import com.shop.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;

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

    public ResponseEntity<?> saveProduct(ProductDto product) throws IOException {
        File dir = new File("/upload");
        dir.mkdir();
        ObjectMapper mapper = new ObjectMapper();
        Category category = mapper.readValue(product.getCategory().toString(), Category.class);
        Family family = mapper.readValue(product.getFamily().toString(), Family.class);
        Product product1 = new Product();
        product1.setDescription(product.getDescription());
        product1.setFamily(family);
        product1.setCategory(category);
        product1.setPrice(product.getPrice());
        product1.setName(product.getName());
        product.getImage().transferTo(Paths.get(dir.getAbsolutePath() +"\\"+product.getImage().getOriginalFilename()));

        product1.setImage(dir.getAbsolutePath() +"\\"+product.getImage().getOriginalFilename());

        productRepo.save(product1);
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
        Product productDB = productRepo.findById(productId).get();
        if (productDB.getImage()!=null) {
            File fileImg = new File(productDB.getImage());
            fileImg.delete();
        }
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

    public ResponseEntity<?> updateProduct(ProductDto product) throws IOException {
        File dir = new File("/upload");
        dir.mkdir();
        ObjectMapper mapper = new ObjectMapper();
        Category category = mapper.readValue(product.getCategory().toString(), Category.class);
        Family family = mapper.readValue(product.getFamily().toString(), Family.class);
        Product productDB = productRepo.findById(product.getId()).get();
        productDB.setDescription(product.getDescription());
        productDB.setFamily(family);
        productDB.setCategory(category);
        productDB.setPrice(product.getPrice());
        productDB.setName(product.getName());
        if (productDB.getImage()!=null) {
            File fileImg = new File(productDB.getImage());
            fileImg.delete();
        }

        product.getImage().transferTo(Paths.get(dir.getAbsolutePath() +"\\"+product.getImage().getOriginalFilename()));
        productDB.setImage(dir.getAbsolutePath() +"\\"+product.getImage().getOriginalFilename());
        productRepo.save(productDB);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<?> changeProductStatus(Long id, boolean active) {
        Product product = productRepo.findById(id).get();
        product.setActive(active);
        productRepo.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
