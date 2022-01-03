package com.fox.shop.ordering.entity.listener;

import com.fox.shop.ordering.entity.OrderItemEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrderItemEntityListener extends AbstractMongoEventListener<OrderItemEntity> {

  @Override
  public void onBeforeConvert(BeforeConvertEvent<OrderItemEntity> event) {
    event.getSource().setId(new Date().getTime());
    super.onBeforeConvert(event);
  }

  @Override
  public void onBeforeSave(BeforeSaveEvent<OrderItemEntity> event) {
    super.onBeforeSave(event);
  }
}
