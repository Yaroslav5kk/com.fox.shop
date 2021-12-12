package com.fox.shop.notify.api.client;

import com.fox.shop.notify.api.client.i.NotifyBotApiClient;
import com.fox.shop.notify.api.factory.i.NotifyBotRequestFactory;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class NotifyBotApiClientImpl implements NotifyBotApiClient {

  private final NotifyBotRequestFactory notifyBotRequestFactory;
  private final WebClient webClient;

  public NotifyBotApiClientImpl(
      final NotifyBotRequestFactory notifyBotRequestFactory
  ) {
    this.notifyBotRequestFactory = notifyBotRequestFactory;
    webClient = WebClient.create();
  }

  @Override
  public Mono<NotifyResponse> order(
      final OrderNotifyRequest request
  ) {
    return notifyBotRequestFactory.orderUri(webClient, request).retrieve().bodyToMono(NotifyResponse.class);
  }
}




























