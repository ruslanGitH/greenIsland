package com.shop.controllers;

import com.shop.model.entity.Client;
import com.shop.model.enums.Role;
import com.shop.model.repository.IClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginPageController {
    @Autowired
    IClientRepo clientRepo;

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveFamily(@RequestBody Client client) {
        Client client1 = clientRepo.findByRole(Role.ADMIN);
        if (client1.getUsername().equals(client.getUsername()) && client.getPassword().equals(client1.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
}
