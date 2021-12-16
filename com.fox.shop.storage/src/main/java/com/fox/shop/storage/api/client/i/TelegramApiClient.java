package com.fox.shop.storage.api.client.i;

import com.fox.shop.storage.config.TelegramConfig;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface TelegramApiClient {
  Mono<Message> sendPhoto(
      String filePath,
      TelegramConfig telegramConfig
  ) throws IOException;
}
