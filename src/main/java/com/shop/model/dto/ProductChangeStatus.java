package com.shop.model.dto;

import com.shop.model.enums.ClientOrderStatus;
import lombok.Data;

@Data
public class ProductChangeStatus {
    private Long id;
    private boolean active;
}
