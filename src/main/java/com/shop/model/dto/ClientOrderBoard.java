package com.shop.model.dto;

import com.shop.model.enums.ClientOrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class ClientOrderBoard {
    private String clientPhoneNumber;
    private String clientMail;
    private List<OrdersDto> productList;
    private ClientOrderStatus status;
}
