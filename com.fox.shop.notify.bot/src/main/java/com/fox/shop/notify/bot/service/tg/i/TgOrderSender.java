package com.fox.shop.notify.bot.service.tg.i;

import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

public interface TgOrderSender {
  Mono<Message> notifyOrder(
      OrderNotifyRequest notifyRequest,
      String chatId
  );
}
