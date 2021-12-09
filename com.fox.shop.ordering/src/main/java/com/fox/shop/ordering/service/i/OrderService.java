package com.fox.shop.ordering.service.i;

import com.fox.shop.ordering.protocol.model.OrderModel;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;

public interface OrderService {
    OrderModel initOrder(OrderOnCreateRequest request);

    OrderModel save(OrderOnCreateRequest request);

    OrderModel get(String id);
}
