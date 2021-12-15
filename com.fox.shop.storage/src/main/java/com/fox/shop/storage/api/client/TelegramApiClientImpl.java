package com.fox.shop.storage.api.client;

import com.fox.shop.storage.api.client.i.TelegramApiClient;
import com.fox.shop.storage.api.request.i.TelegramRequestFactory;
import com.fox.shop.storage.config.TelegramConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

@Component
public class TelegramApiClientImpl implements TelegramApiClient {
  private final TelegramRequestFactory telegramRequestFactory;

  public TelegramApiClientImpl(
      final TelegramRequestFactory telegramRequestFactory
  ) {
    this.telegramRequestFactory = telegramRequestFactory;
  }

  @Override
  public Mono<Message> sendPhoto(
      final String filePath,
      final TelegramConfig telegramConfig
  ) {
    return telegramRequestFactory.sendPhoto(telegramConfig, filePath).retrieve().bodyToMono(Message.class);
  }
}
