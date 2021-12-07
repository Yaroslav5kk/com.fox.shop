package com.fox.shop.notify.api.factory.i;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import org.springframework.web.reactive.function.client.WebClient;

public interface NotifyBotRequestFactory extends FatherRequestFactory{
  WebClient.RequestHeadersSpec<?> orderUri(
      WebClient webClient,
      OrderNotifyRequest request
  );
}
