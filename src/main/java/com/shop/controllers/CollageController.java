package com.shop.controllers;

import com.shop.model.entity.Collage;
import com.shop.service.AdminPageService;
import com.shop.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CollageController {
    @Autowired
    private AdminPageService adminPageService;
    @Autowired
    private MainPageService mainPageService;

    @RequestMapping(value = "admin/collage", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveCollage(@RequestBody Collage collage) {
        return adminPageService.saveCollage(collage);
    }

    @GetMapping("/collage")
    public ResponseEntity<?> getCollage() {
        return mainPageService.getCollage();
    }
}

