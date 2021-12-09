package com.fox.shop.notify.bot.service.tg.i;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import reactor.core.publisher.Mono;

public interface TgOrderSender {
  Mono<Void> notifyOrder(
      OrderNotifyRequest notifyRequest,
      String chatId
  );
}
