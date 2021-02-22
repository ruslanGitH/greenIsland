package com.shop.controllers;

import com.shop.model.dto.ClientConnectInfo;
import com.shop.model.dto.ClientOrderBoard;
import com.shop.model.dto.OrdersDto;
import com.shop.model.dto.ProductDto;
import com.shop.model.entity.ClientOrder;
import com.shop.model.repository.IClientOrderRepo;
import com.shop.model.repository.IClientRepo;
import com.shop.model.repository.IOrderRepo;
import com.shop.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
//        orders.getOrders().stream().forEach(x->x.setClientOrder(orders));
        orders.setDate(LocalDateTime.now());
        clientRepo.save(orders.getClient());
        orders.setPrice(orders.getOrders().stream().mapToDouble(x -> x.getProduct().getPrice()).sum());
        clientOrderRepo.save(orders);
        orderRepo.saveAll(orders.getOrders());
//        mailService.send(orders);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/admin/clientOrders")
    public ResponseEntity<?> getClientOrders() {
        List<ClientOrder> clientOrders = new ArrayList<>();
        clientOrderRepo.findAll().forEach(clientOrders::add);

        List<ClientOrderBoard> clientOrderBoards = new ArrayList<>();
        for (ClientOrder order : clientOrders) {
            ClientOrderBoard clientOrderBoard = new ClientOrderBoard();
            clientOrderBoard.setClientMail(order.getClient().getEmail());
            clientOrderBoard.setClientPhoneNumber(order.getClient().getPhoneNumber());
            clientOrderBoard.setStatus(order.getStatus());

            List<OrdersDto> ordersDto = new ArrayList<>();

            order.getOrders().forEach(o -> ordersDto.add(new OrdersDto(o.getCount(),
                    new ProductDto(o.getProduct().getName(), o.getProduct().getPrice(), o.getProduct().getDescription(),
                            o.getProduct().getFamily().getName(), o.getProduct().getCategory().getName()))));


            clientOrderBoard.setProductList(ordersDto);

            clientOrderBoards.add(clientOrderBoard);
        }


//        clientOrders.stream().map(ClientOrder::getOrders).forEach(x -> x.stream().iterator().next().setClientOrder(null));
        return ResponseEntity.ok().body(clientOrderBoards);
    }

    @RequestMapping(value = "connectWithAdmin", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> connectWithAdmin(@RequestBody ClientConnectInfo clientConnectInfo) {
        mailService.connectWithAdmin(clientConnectInfo.getMail(), clientConnectInfo.getClientName(), clientConnectInfo.getMessageText());
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}