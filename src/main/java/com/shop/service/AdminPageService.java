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

    public ResponseEntity<?> saveProduct(ProductDto product, MultipartFile file) throws IOException {
        File dir = new File("/imgDB");
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
        file.transferTo(dir);
//        String imgName = LocalDateTime.now().toString();
//        byte[] imgByte = product.getImage();
//        FileOutputStream fileOutputStream = new FileOutputStream(new File(dir.getAbsolutePath() + "/" + 1));
//        fileOutputStream.write(imgByte);
//        fileOutputStream.flush();
//        fileOutputStream.close();
//
//        ByteArrayResource arrayResource = new ByteArrayResource(product.getImage());
//        arrayResource.getFile();

        product1.setImage(dir.getAbsolutePath() + "/" + file.getName());

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

    public ResponseEntity<?> updateProduct(ProductDto product, MultipartFile file) throws IOException {
        File folder = new File("db/productPhotos");
        folder.mkdir();
        file.transferTo(folder);

        Product product1 = productRepo.findById(product.getId()).get();
        product1.setImage(folder.getAbsolutePath() + "/" + file.getName());
        productRepo.save(product1);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
