package com.shop.model.repository;

import com.shop.model.entity.Client;
import com.shop.model.entity.Family;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFamilyRepo extends CrudRepository<Family, Long> {
}
