package com.shop.service;

import com.shop.model.dto.ProductWithImage;
import com.shop.model.entity.Product;
import com.shop.model.repository.ICollageRepo;
import com.shop.model.repository.IProductRepo;
import com.shop.model.repository.IShopInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
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

//    public ResponseEntity<?> getAllProductsList() {
//        return ResponseEntity.status(HttpStatus.OK).body(productRepo.findAll());
//    }

    public ResponseEntity<?> getActiveProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productRepo.findByActiveIsTrue());
    }

    public ResponseEntity<?> getShopInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(shopInfoRepo.findAll());
    }

    public ResponseEntity<?> getCollage() {
        return ResponseEntity.status(HttpStatus.OK).body(collageRepo.findAll());
    }

    public ResponseEntity<?> getProduct(Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productRepo.findById(productId));
    }

    public ResponseEntity<?> getAllProductsList() throws IOException {
        List<Product> all = productRepo.findAll();
        List<ProductWithImage> productWithImages = new ArrayList<>();
        for (Product product : all) {
            if (product.getImage() != null) {
                File file = new File(product.getImage());
                try {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    WritableRaster writableRaster = bufferedImage.getRaster();
                    DataBufferByte dataBufferByte = (DataBufferByte) writableRaster.getDataBuffer();
                    productWithImages.add(new ProductWithImage(product, dataBufferByte.getData()));
                } catch (Exception e) {
                    productWithImages.add(new ProductWithImage(product, null));
                }
            } else
                productWithImages.add(new ProductWithImage(product, null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(productWithImages);
    }
}
