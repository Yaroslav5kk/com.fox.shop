package com.fox.shop.storage.api.client.i;

import com.fox.shop.storage.config.TelegramConfig;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

public interface TelegramApiClient {
  Mono<Message> sendPhoto(
      String filePath,
      TelegramConfig telegramConfig
  ) ;

  Mono<Message> sendAnimation(
      String fileUrl,
      TelegramConfig telegramConfig
  );

  Mono<Message> sendVideo(
      String fileUrl,
      TelegramConfig telegramConfig
  );
}
