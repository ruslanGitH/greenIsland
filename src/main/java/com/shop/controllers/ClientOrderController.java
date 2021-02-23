package com.shop.controllers;

import com.shop.model.dto.ClientConnectInfo;
import com.shop.model.entity.ClientOrder;
import com.shop.service.ClientOrderService;
import com.shop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClientOrderController {

    @Autowired
    private MailService mailService;
    @Autowired
    private ClientOrderService orderService;


    @RequestMapping(value = "clientOrder", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addOrder(@RequestBody ClientOrder orders) {
        orderService.addClientOrder(orders);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/admin/clientOrders")
    public ResponseEntity<?> getClientOrders() {
        return orderService.getClientOrders();
    }
    @GetMapping("/admin/newClientOrders")
    public ResponseEntity<?> getNewClientOrders() {
        return orderService.getNewClientOrders();
    }
    @GetMapping("/admin/processingClientOrders")
    public ResponseEntity<?> getProcessingClientOrders() {
        return orderService.getProcessingClientOrders();
    }
    @GetMapping("/admin/executedClientOrders")
    public ResponseEntity<?> getExecutedClientOrders() {
        return orderService.getExecutedClientOrders();
    }

    @RequestMapping(value = "connectWithAdmin", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> connectWithAdmin(@RequestBody ClientConnectInfo clientConnectInfo) {
        mailService.connectWithAdmin(clientConnectInfo.getMail(), clientConnectInfo.getClientName(), clientConnectInfo.getMessageText());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}