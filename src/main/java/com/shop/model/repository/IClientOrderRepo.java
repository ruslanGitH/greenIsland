package com.shop.model.repository;

import com.shop.model.entity.Client;
import com.shop.model.entity.ClientOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientOrderRepo extends CrudRepository<ClientOrder, Long> {
}
