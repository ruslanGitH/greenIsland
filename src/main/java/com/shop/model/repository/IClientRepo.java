package com.shop.model.repository;

import com.shop.model.entity.Client;
import com.shop.model.enums.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepo extends CrudRepository<Client, Long> {
    Client findByRole(Role role);
}
