package com.fox.shop.ordering.converter.i;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.ordering.entity.OrderEntity;
import com.fox.shop.ordering.protocol.model.OrderModel;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;

import java.util.Map;

public interface OrderConverter {
    OrderEntity fromOnCreateRequestToEntity(OrderOnCreateRequest request);

    OrderModel fromEntityToModel(OrderEntity entity);

    OrderNotifyRequest fromEntityToOrderNotifyRequest(OrderEntity entity, Map<Long, FullCartItemModel> productIdCartItem);
}
