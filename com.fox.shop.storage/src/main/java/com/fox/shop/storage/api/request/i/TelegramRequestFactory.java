package com.fox.shop.storage.api.request.i;

import com.fox.shop.storage.config.TelegramConfig;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public interface TelegramRequestFactory {
  WebClient.RequestHeadersSpec<?> sendPhoto(
      TelegramConfig telegramConfig,
      String filePath
  );
}
