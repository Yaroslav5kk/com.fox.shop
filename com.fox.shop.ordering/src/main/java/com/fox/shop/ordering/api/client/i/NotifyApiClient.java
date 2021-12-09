package com.fox.shop.ordering.api.client.i;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;

public interface NotifyApiClient {
    /*--------------------------------------------- order ----------------------------------------------------*/

    String notifyOrder(OrderNotifyRequest request);
}
