package com.shop.controllers;

import com.shop.model.entity.ClientOrder;
import com.shop.model.repository.IClientOrderRepo;
import com.shop.model.repository.IClientRepo;
import com.shop.model.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientOrderController {
    @Autowired
    private IOrderRepo orderRepo;
    @Autowired
    private IClientRepo clientRepo;
    @Autowired
    private IClientOrderRepo clientOrderRepo;

    @RequestMapping(value = "clientOrder", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addOrder(@RequestBody ClientOrder orders) {
        clientRepo.save(orders.getClient());
        clientOrderRepo.save(orders);
        orderRepo.saveAll(orders.getOrders());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
