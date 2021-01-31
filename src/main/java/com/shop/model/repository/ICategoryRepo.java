package com.shop.model.repository;

import com.shop.model.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepo extends CrudRepository<Category, Long> {
}
