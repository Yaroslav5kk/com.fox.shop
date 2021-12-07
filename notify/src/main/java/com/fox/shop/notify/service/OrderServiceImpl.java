package com.fox.shop.notify.service;

import com.fox.shop.notify.api.client.i.NotifyBotApiClient;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService {

  private final NotifyBotApiClient notifyBotApiClient;

  public OrderServiceImpl(
      final NotifyBotApiClient notifyBotApiClient
  ) {
    this.notifyBotApiClient = notifyBotApiClient;
  }

  @Override
  public Mono<NotifyResponse> notifyOrder(
      final long orderId
  ) {
    final OrderNotifyRequest request = new OrderNotifyRequest();
    return notifyBotApiClient.order(request);
  }
}
