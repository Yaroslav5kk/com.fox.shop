package com.fox.shop.ordering.api.factory.i;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import org.apache.http.client.methods.HttpUriRequest;

public interface NotifyRequestFactory {

    /*----------------------------------------------order-------------------------------------------------*/

    HttpUriRequest notifyOrder(OrderNotifyRequest request);

}
