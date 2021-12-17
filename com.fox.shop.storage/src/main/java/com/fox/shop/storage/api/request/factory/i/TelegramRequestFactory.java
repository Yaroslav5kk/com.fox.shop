package com.fox.shop.storage.api.request.factory.i;

import com.fox.shop.storage.config.TelegramConfig;
import org.springframework.web.reactive.function.client.WebClient;

public interface TelegramRequestFactory {
  WebClient.RequestHeadersSpec<?> sendPhoto(
      TelegramConfig telegramConfig,
      String filePath
  );

  WebClient.RequestHeadersSpec<?> sendAnimation(
      TelegramConfig telegramConfig,
      String fileUrl
  );

  WebClient.RequestHeadersSpec<?> sendVideo(
      TelegramConfig telegramConfig,
      String fileUrl
  );
}
