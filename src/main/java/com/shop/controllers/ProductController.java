package com.shop.controllers;

import com.shop.model.dto.ProductChangeStatus;
import com.shop.model.dto.ProductDto;
import com.shop.service.AdminPageService;
import com.shop.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ProductController {
    @Autowired
    private AdminPageService adminPageService;
    @Autowired
    private MainPageService mainPageService;

    @RequestMapping(value = "admin/product", method = RequestMethod.POST, produces = "multipart/form-data", consumes = "multipart/form-data")
    public ResponseEntity<?> saveProduct(@ModelAttribute ProductDto file) throws IOException {
        return adminPageService.saveProduct(file);
    }

    @RequestMapping(value = "admin/product/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeProduct(@PathVariable("productId") Long productId) {
        return adminPageService.deleteProduct(productId);
    }

    @RequestMapping(value = "admin/product", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> changeProductStatus(@RequestBody ProductChangeStatus changeStatus) {
        return adminPageService.changeProductStatus(changeStatus.getId(), changeStatus.isActive());
    }


    @RequestMapping(value = "admin/product", method = RequestMethod.PUT, produces = "multipart/form-data", consumes = "multipart/form-data")
    public ResponseEntity<?> updateProduct(@ModelAttribute ProductDto product) throws IOException {
//        return ResponseEntity.ok().body(null);
        return adminPageService.updateProduct(product);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getActiveProducts() {
        return mainPageService.getActiveProducts();
    }

    @GetMapping("/admin/products")
    public ResponseEntity<?> getAllProducts() throws IOException {
        return mainPageService.getAllProductsList();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> getProductWithId(@PathVariable("productId") Long productId) {
        return mainPageService.getProduct(productId);
    }

    @GetMapping("/product/img/{name}")
    public ResponseEntity<byte[]> getProductWithId(@PathVariable("name") String name, final HttpServletResponse response) throws IOException {
        if (!name.equals("null"))
            return adminPageService.getPhotoByName(name, response);
        else return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
