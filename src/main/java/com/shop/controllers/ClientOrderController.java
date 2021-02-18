package com.shop.controllers;

import com.shop.model.entity.ClientOrder;
import com.shop.model.repository.IClientOrderRepo;
import com.shop.model.repository.IClientRepo;
import com.shop.model.repository.IOrderRepo;
import com.shop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientOrderController {
    @Autowired
    private IOrderRepo orderRepo;
    @Autowired
    private IClientRepo clientRepo;
    @Autowired
    private IClientOrderRepo clientOrderRepo;
    @Autowired
    private MailService mailService;


    @RequestMapping(value = "clientOrder", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addOrder(@RequestBody ClientOrder orders) {
        orders.getOrders().stream().forEach(x->x.setClientOrder(orders));
        clientRepo.save(orders.getClient());
        clientOrderRepo.save(orders);
        orderRepo.saveAll(orders.getOrders());
        mailService.send(orders);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @RequestMapping(value = "connectWithAdmin", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addOrder(@PathVariable String mail, @PathVariable String name,  @PathVariable String text) {
        mailService.connectWithAdmin(mail, name, text);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
