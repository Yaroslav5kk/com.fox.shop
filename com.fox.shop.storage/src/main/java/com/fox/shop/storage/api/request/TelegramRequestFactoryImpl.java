package com.fox.shop.storage.api.request;

import com.fox.shop.storage.config.TelegramConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Component
public class TelegramRequestFactoryImpl implements FatherRequestFactory {
  @Value("telegram.api.endpoints.send-photo")
  private String sendPhotoPath;

  private final WebClient webClient;

  public TelegramRequestFactoryImpl(
  ) {
    this.webClient = WebClient.create();
  }

  @Override
  public WebClient.RequestHeadersSpec<?> sendPhoto(
      final TelegramConfig telegramConfig
      ) {
    return webClient
        .post()
        .uri(buildFullUri(
            basePath,
            sendPhotoPath,
            Collections.emptyList()
        ))
        .bodyValue(request);
  }
}
