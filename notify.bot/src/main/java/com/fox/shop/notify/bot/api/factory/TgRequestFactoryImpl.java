package com.fox.shop.notify.bot.api.factory;

import com.fox.shop.notify.bot.api.factory.i.TgRequestFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class TgRequestFactoryImpl implements TgRequestFactory {

  @Value("${telegram.api.url}")
  private String basePath;
  @Value("${telegram.api.endpoints.send-message}")
  private String sendMessagePath;

  @Override
  public WebClient.RequestHeadersSpec<?> sendMessage(
      final WebClient webClient,
      final SendMessage request
  ) {
    return webClient.post().uri(basePath + sendMessagePath).bodyValue(request);
  }
}
