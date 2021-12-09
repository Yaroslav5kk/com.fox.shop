package com.fox.shop.notify.service;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;
import reactor.core.publisher.Mono;

public interface OrderService {
  Mono<NotifyResponse> notifyOrder(
          OrderNotifyRequest request
  );
}
