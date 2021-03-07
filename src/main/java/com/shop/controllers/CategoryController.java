package com.shop.controllers;

import com.shop.model.entity.Category;
import com.shop.service.AdminPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @Autowired
    private AdminPageService adminPageService;

    @RequestMapping(value = "admin/category", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        return adminPageService.saveCategory(category);
    }

    @RequestMapping(value = "admin/category", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        return adminPageService.saveCategory(category);
    }

    @RequestMapping(value = "admin/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeCategory(@PathVariable("id") Long id) {
        return adminPageService.deleteCategory(id);
    }

    @GetMapping("/category")
    public ResponseEntity<?> getCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(adminPageService.getCategory());
    }
}
