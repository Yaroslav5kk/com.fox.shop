package com.fox.shop.base.repository;

import com.fox.shop.base.entity.ProductBalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBalanceRepository extends JpaRepository<ProductBalanceEntity, Long> {
}
