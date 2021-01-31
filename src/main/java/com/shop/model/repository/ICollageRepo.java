package com.shop.model.repository;

import com.shop.model.entity.Client;
import com.shop.model.entity.Collage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICollageRepo extends CrudRepository<Collage, Long> {
}
