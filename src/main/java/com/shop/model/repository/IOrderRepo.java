package com.shop.model.repository;

import com.shop.model.entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface IOrderRepo extends CrudRepository<Orders,Long> {
}
