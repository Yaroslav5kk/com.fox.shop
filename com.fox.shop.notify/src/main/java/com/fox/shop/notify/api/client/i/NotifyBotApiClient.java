package com.fox.shop.notify.api.client.i;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;
import reactor.core.publisher.Mono;

public interface NotifyBotApiClient extends FatherApiClient{
  Mono<NotifyResponse> order(
      OrderNotifyRequest request
  );
}
