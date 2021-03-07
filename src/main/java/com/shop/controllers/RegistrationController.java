package com.shop.controllers;

import com.shop.model.entity.Client;
import com.shop.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "registration", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> addUser(@RequestBody Client user) {
        return clientService.addClient(user);
    }
}
