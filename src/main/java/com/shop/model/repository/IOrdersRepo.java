package com.shop.model.repository;

import com.shop.model.entity.Family;
import com.shop.model.entity.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepo extends CrudRepository<Orders, Long> {
}
