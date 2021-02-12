package com.shop.controllers;

import com.shop.model.entity.Family;
import com.shop.service.AdminPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FamilyController {
    @Autowired
    private AdminPageService adminPageService;

    @RequestMapping(value = "admin/family", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveFamily(@RequestBody Family family) {
        return adminPageService.saveFamily(family);
    }

    @RequestMapping(value = "admin/family/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeFamily(@PathVariable("id") Long id) {
        return adminPageService.deleteFamily(id);
    }


}
