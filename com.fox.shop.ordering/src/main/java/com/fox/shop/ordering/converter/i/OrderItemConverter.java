package com.fox.shop.ordering.converter.i;

import com.fox.shop.ordering.entity.OrderItemEntity;
import com.fox.shop.ordering.protocol.model.OrderItemModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;

public interface OrderItemConverter {

    OrderItemEntity fromOrderItemToEntity(OrderItemModel request);

    OrderItemEntity fromFullCartItemToEntity(
            FullCartItemModel model,
            int priceAtOne
    );

    OrderItemModel fromEntityToModel(OrderItemEntity entity);
}
