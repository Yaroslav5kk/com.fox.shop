package com.fox.shop.storage.api.request.factory.i;

import com.fox.shop.storage.config.TelegramConfig;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

public interface TelegramRequestFactory {
  WebClient.RequestHeadersSpec<?> sendPhoto(
      TelegramConfig telegramConfig,
      String filePath
  ) throws IOException;
}
