package com.shop.controllers;

import com.shop.model.entity.ShopInfo;
import com.shop.service.AdminPageService;
import com.shop.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopInfoController {
    @Autowired
    private AdminPageService adminPageService;

    @Autowired
    private MainPageService mainPageService;

    @GetMapping("/shopInfo")
    public ResponseEntity<?> getShopInfo(){
        return mainPageService.getShopInfo();
    }

    @RequestMapping(value = "admin/shopInfo", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<?> saveShopInfo(@RequestBody ShopInfo shopInfo) {
        return adminPageService.saveShopInfo(shopInfo);
    }
}
