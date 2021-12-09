package com.fox.shop.shoppingcart.base.entity.listener;

import com.fox.shop.shoppingcart.base.entity.CartItemEntity;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Component
public class CartItemListener {

  @PrePersist
  private void prePersist(CartItemEntity cartItemEntity) {
    final Date now = new Date();
    cartItemEntity.setCreatedAt(now);
    cartItemEntity.setUpdatedAt(now);
  }

  @PreUpdate
  private void preUpdate(CartItemEntity cartItemEntity) {
    final Date now = new Date();
    cartItemEntity.setUpdatedAt(now);
  }
}
