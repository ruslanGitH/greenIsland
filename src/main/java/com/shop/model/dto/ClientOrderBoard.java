package com.shop.model.dto;

import com.shop.model.enums.ClientOrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ClientOrderBoard {
    private Long id;
    private String clientPhoneNumber;
    private String clientMail;
    private List<OrdersDto> productList;
    private ClientOrderStatus status;
    private double price;
    private LocalDateTime dateTime;
}
