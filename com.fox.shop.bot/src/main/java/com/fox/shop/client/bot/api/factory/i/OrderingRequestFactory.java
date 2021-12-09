package com.fox.shop.client.bot.api.factory.i;

import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import org.apache.http.client.methods.HttpUriRequest;

public interface OrderingRequestFactory {
    HttpUriRequest initOrder(
            OrderOnCreateRequest request
    );
}
