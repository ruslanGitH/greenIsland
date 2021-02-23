package com.shop.service;

import com.shop.model.dto.ChangeStatus;
import com.shop.model.dto.ClientOrderBoard;
import com.shop.model.dto.OrdersDto;
import com.shop.model.dto.ProductDtoForSend;
import com.shop.model.entity.ClientOrder;
import com.shop.model.enums.ClientOrderStatus;
import com.shop.model.repository.IClientOrderRepo;
import com.shop.model.repository.IClientRepo;
import com.shop.model.repository.IOrderRepo;
import com.shop.model.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientOrderService {
    @Autowired
    private IClientOrderRepo clientOrderRepo;
    @Autowired
    private IClientRepo clientRepo;
    @Autowired
    private IOrderRepo orderRepo;
    @Autowired
    private IProductRepo productRepo;

    public ResponseEntity<?> getClientOrders() {
        List<ClientOrder> clientOrders = new ArrayList<>();
        clientOrderRepo.findAll().forEach(clientOrders::add);

        List<ClientOrderBoard> clientOrderBoards = new ArrayList<>();
        for (ClientOrder order : clientOrders) {
            ClientOrderBoard clientOrderBoard = new ClientOrderBoard();
            clientOrderBoard.setClientMail(order.getClient().getEmail());
            clientOrderBoard.setClientPhoneNumber(order.getClient().getPhoneNumber());
            clientOrderBoard.setDateTime(order.getDate());
            clientOrderBoard.setStatus(order.getStatus());
            clientOrderBoard.setPrice(order.getPrice());

            List<OrdersDto> ordersDto = new ArrayList<>();

            order.getOrders().forEach(o -> ordersDto.add(new OrdersDto(o.getCount(),
                    new ProductDtoForSend(o.getProduct().getName(), o.getProduct().getPrice(), o.getProduct().getDescription(),
                            o.getProduct().getFamily().getName(), o.getProduct().getCategory().getName()))));


            clientOrderBoard.setProductList(ordersDto);
            clientOrderBoards.add(clientOrderBoard);
        }
        return ResponseEntity.ok().body(clientOrderBoards);
    }


    public void addClientOrder(ClientOrder orders) {
        orders.getOrders().forEach(x -> x.setClientOrder(orders));
        orders.setStatus(ClientOrderStatus.NEW);
        orders.setDate(LocalDateTime.now());
        clientRepo.save(orders.getClient());
        orders.setPrice(orders.getPrice());
        clientOrderRepo.save(orders);
        orderRepo.saveAll(orders.getOrders());
    }

    public ResponseEntity<?> getExecutedClientOrders() {
        return ResponseEntity.ok().body(getClientOrderWithStatus(ClientOrderStatus.EXECUTED));
    }

    public ResponseEntity<?> getNewClientOrders() {
        return ResponseEntity.ok().body(getClientOrderWithStatus(ClientOrderStatus.NEW));
    }

    public ResponseEntity<?> getProcessingClientOrders() {
        return ResponseEntity.ok().body(getClientOrderWithStatus(ClientOrderStatus.PROCESSING));
    }


    public List<ClientOrderBoard> getClientOrderWithStatus(ClientOrderStatus status) {
        List<ClientOrder> clientOrders = new ArrayList<>();
        clientOrderRepo.findAll().forEach(clientOrders::add);

        List<ClientOrderBoard> clientOrderBoards = new ArrayList<>();
        for (ClientOrder order : clientOrders.stream().filter(x -> x.getStatus() == status).collect(Collectors.toList())) {
            ClientOrderBoard clientOrderBoard = new ClientOrderBoard();
            clientOrderBoard.setId(order.getId());
            clientOrderBoard.setClientMail(order.getClient().getEmail());
            clientOrderBoard.setClientPhoneNumber(order.getClient().getPhoneNumber());
            clientOrderBoard.setStatus(order.getStatus());
            clientOrderBoard.setDateTime(order.getDate());
            clientOrderBoard.setPrice(order.getPrice());

            List<OrdersDto> ordersDto = new ArrayList<>();

            order.getOrders().forEach(o -> ordersDto.add(new OrdersDto(o.getCount(),
                    new ProductDtoForSend(o.getProduct().getName(), o.getProduct().getPrice(), o.getProduct().getDescription(),
                            o.getProduct().getFamily().getName(), o.getProduct().getCategory().getName()))));


            clientOrderBoard.setProductList(ordersDto);
            clientOrderBoards.add(clientOrderBoard);
        }
        return clientOrderBoards;
    }

    public ResponseEntity<?> changeStatus(ChangeStatus changeStatus) {
            ClientOrder clientOrder = clientOrderRepo.findById(changeStatus.getId()).get();
            clientOrder.setStatus(changeStatus.getStatus());
            clientOrderRepo.save(clientOrder);
            return ResponseEntity.ok().body(null);

    }
}
