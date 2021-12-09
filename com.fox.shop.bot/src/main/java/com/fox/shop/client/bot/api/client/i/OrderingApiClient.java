package com.fox.shop.client.bot.api.client.i;

import com.fox.shop.ordering.protocol.model.OrderModel;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;

public interface OrderingApiClient {

    OrderModel initOrder(
            OrderOnCreateRequest request
    );
}
