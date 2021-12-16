package com.fox.shop.storage.api.request.factory;

import com.fox.shop.storage.api.request.factory.i.FatherRequestFactory;
import com.fox.shop.storage.api.request.factory.i.TelegramRequestFactory;
import com.fox.shop.storage.api.request.model.TgSendPhotoBinaryRequest;
import com.fox.shop.storage.config.TelegramConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Collections;

@Component
public class TelegramRequestFactoryImpl implements FatherRequestFactory, TelegramRequestFactory {
  @Value("${telegram.api.endpoints.send-photo}")
  private String sendPhotoPath;

  private final WebClient webClient;

  public TelegramRequestFactoryImpl(
  ) {
    this.webClient = WebClient.create();
  }

  @Override
  public WebClient.RequestHeadersSpec<?> sendPhoto(
      final TelegramConfig telegramConfig,
      final String filePath
  ) throws IOException {
    final TgSendPhotoBinaryRequest requestBody = new TgSendPhotoBinaryRequest();
    requestBody.setPhoto(new PathResource(filePath).getInputStream().readAllBytes());
    requestBody.setChatId(telegramConfig.getChatId());
    return webClient
        .post()
        .uri(buildFullUri(
            telegramConfig.getBaseUrl(),
            sendPhotoPath,
            Collections.emptyList()
        ))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(requestBody);
  }

}
