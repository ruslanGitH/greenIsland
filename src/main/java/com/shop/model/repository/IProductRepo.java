package com.shop.model.repository;

import com.shop.model.entity.Category;
import com.shop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByActiveIsTrue();
    List<Product> findByCategoryIs(Category category);
}
