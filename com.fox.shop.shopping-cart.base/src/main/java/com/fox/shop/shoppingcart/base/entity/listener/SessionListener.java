package com.fox.shop.shoppingcart.base.entity.listener;

import com.fox.shop.shoppingcart.base.entity.SessionEntity;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Component
public class SessionListener {

  @PrePersist
  private void prePersist(SessionEntity sessionEntity) {
    final Date now = new Date();
    sessionEntity.setCreatedAt(now);
    sessionEntity.setUpdatedAt(now);
  }

  @PreUpdate
  private void preUpdate(SessionEntity sessionEntity) {
    final Date now = new Date();
    sessionEntity.setUpdatedAt(now);
  }
}
