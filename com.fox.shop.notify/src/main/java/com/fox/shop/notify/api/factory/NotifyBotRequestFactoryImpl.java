package com.fox.shop.notify.api.factory;

import com.fox.shop.notify.api.factory.i.NotifyBotRequestFactory;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NotifyBotRequestFactoryImpl implements NotifyBotRequestFactory {

  @Value("${notify-telegram-bot.api.url}")
  private String basePath;
  @Value("${notify-telegram-bot.api.endpoints.notify-order}")
  private String notifyOrderPath;

  @Override
  public WebClient.RequestHeadersSpec<?> orderUri(
      final WebClient webClient,
      final OrderNotifyRequest request
  ) {
    return webClient.post().uri(basePath + notifyOrderPath).bodyValue(request);
  }
}






























