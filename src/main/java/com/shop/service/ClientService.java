package com.shop.service;

import com.shop.model.entity.Client;
import com.shop.model.repository.IClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private IClientRepo clientRepo;

    public ResponseEntity<?> addClient(Client client){
        clientRepo.save(client);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
