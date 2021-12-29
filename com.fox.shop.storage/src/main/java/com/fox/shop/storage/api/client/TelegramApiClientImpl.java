package com.fox.shop.storage.api.client;

import com.fox.shop.storage.api.client.i.TelegramApiClient;
import com.fox.shop.storage.api.request.factory.i.TelegramRequestFactory;
import com.fox.shop.storage.api.request.model.GeneralTelegramResponse;
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
          final String fileUrl,
          final TelegramConfig telegramConfig
  ) {
    return telegramRequestFactory.sendPhoto(telegramConfig, fileUrl).retrieve().bodyToMono(GeneralTelegramResponse.class)
            .map(GeneralTelegramResponse::getResult);
  }

  @Override
  public Mono<Message> sendAnimation(
          final String fileUrl,
          final TelegramConfig telegramConfig
  ) {
    return telegramRequestFactory.sendAnimation(telegramConfig, fileUrl).retrieve().bodyToMono(GeneralTelegramResponse.class)
            .map(GeneralTelegramResponse::getResult);
  }

  @Override
  public Mono<Message> sendVideo(
          final String fileUrl,
          final TelegramConfig telegramConfig
  ) {
    return telegramRequestFactory.sendVideo(telegramConfig, fileUrl).retrieve().bodyToMono(Message.class);
  }
}
