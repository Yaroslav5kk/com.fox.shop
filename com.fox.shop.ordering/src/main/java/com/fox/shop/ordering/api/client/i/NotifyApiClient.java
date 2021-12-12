package com.fox.shop.ordering.api.client.i;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;

public interface NotifyApiClient {
    /*--------------------------------------------- order ----------------------------------------------------*/

    NotifyResponse notifyOrder(OrderNotifyRequest request);
}
