package com.shop.model.repository;

import com.shop.model.entity.Product;
import com.shop.model.entity.ShopInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopInfoRepo extends CrudRepository<ShopInfo, Long> {
}
