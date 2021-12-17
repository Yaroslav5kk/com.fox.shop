package com.fox.shop.storage.api.request.factory;

import com.fox.shop.storage.api.request.factory.i.FatherRequestFactory;
import com.fox.shop.storage.api.request.factory.i.TelegramRequestFactory;
import com.fox.shop.storage.api.request.model.TgSendPhotoFileIdRequest;
import com.fox.shop.storage.config.TelegramConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Component
public class TelegramRequestFactoryImpl implements FatherRequestFactory, TelegramRequestFactory {
  @Value("${telegram.api.endpoints.send-photo}")
  private String sendPhotoPath;
  @Value("${telegram.api.endpoints.send-animation}")
  private String sendAnimationPath;
  @Value("${telegram.api.endpoints.send-video}")
  private String sendVideoPath;

  private final WebClient webClient;

  public TelegramRequestFactoryImpl(
  ) {
    this.webClient = WebClient.create();
  }

  @Override
  public WebClient.RequestHeadersSpec<?> sendPhoto(
      final TelegramConfig telegramConfig,
      final String fileUrl
  ) {
    final TgSendPhotoFileIdRequest requestBody = new TgSendPhotoFileIdRequest();
    requestBody.setPhoto(fileUrl);
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

  @Override
  public WebClient.RequestHeadersSpec<?> sendAnimation(
      final TelegramConfig telegramConfig,
      final String fileUrl
  ) {
    final TgSendPhotoFileIdRequest requestBody = new TgSendPhotoFileIdRequest();
    requestBody.setPhoto(fileUrl);
    requestBody.setChatId(telegramConfig.getChatId());
    return webClient
        .post()
        .uri(buildFullUri(
            telegramConfig.getBaseUrl(),
            sendAnimationPath,
            Collections.emptyList()
        ))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(requestBody);
  }

  @Override
  public WebClient.RequestHeadersSpec<?> sendVideo(
      final TelegramConfig telegramConfig,
      final String fileUrl
  ) {
    final TgSendPhotoFileIdRequest requestBody = new TgSendPhotoFileIdRequest();
    requestBody.setPhoto(fileUrl);
    requestBody.setChatId(telegramConfig.getChatId());
    return webClient
        .post()
        .uri(buildFullUri(
            telegramConfig.getBaseUrl(),
            sendVideoPath,
            Collections.emptyList()
        ))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .bodyValue(requestBody);
  }

}
