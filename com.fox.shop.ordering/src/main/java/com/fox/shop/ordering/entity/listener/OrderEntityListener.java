package com.fox.shop.ordering.entity.listener;

import com.fox.shop.ordering.entity.OrderEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityListener extends AbstractMongoEventListener<OrderEntity> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<OrderEntity> event) {
        super.onBeforeConvert(event);
    }

    @Override
    public void onBeforeSave(BeforeSaveEvent<OrderEntity> event) {
        super.onBeforeSave(event);
    }
}
