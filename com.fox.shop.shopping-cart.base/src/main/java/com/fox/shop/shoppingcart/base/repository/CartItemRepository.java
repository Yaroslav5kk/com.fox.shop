package com.fox.shop.shoppingcart.base.repository;

import com.fox.shop.shoppingcart.base.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
