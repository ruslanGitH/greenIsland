package com.shop.model.dto;

import com.shop.model.enums.ClientOrderStatus;
import lombok.Data;

@Data
public class ChangeStatus {
    private Long id;
    private ClientOrderStatus status;
}
