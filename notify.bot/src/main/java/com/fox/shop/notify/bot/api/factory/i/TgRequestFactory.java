package com.fox.shop.notify.bot.api.factory.i;

import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface TgRequestFactory extends FatherRequestFactory {
  WebClient.RequestHeadersSpec<?> sendMessage(
      WebClient webClient,
      SendMessage request
  );
}
