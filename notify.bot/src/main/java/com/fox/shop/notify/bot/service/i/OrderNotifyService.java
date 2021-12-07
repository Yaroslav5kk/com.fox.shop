package com.fox.shop.notify.bot.service.i;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;
import reactor.core.publisher.Mono;

public interface OrderNotifyService {
  Mono<NotifyResponse> notifyOrder(
      OrderNotifyRequest request
  );
}
