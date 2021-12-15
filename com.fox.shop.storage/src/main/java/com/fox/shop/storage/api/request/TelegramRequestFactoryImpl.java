package com.fox.shop.storage.api.request;

import com.fox.shop.storage.api.request.i.FatherRequestFactory;
import com.fox.shop.storage.api.request.i.TelegramRequestFactory;
import com.fox.shop.storage.config.TelegramConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
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
  ) {
    return webClient
        .post()
        .uri(buildFullUri(
            telegramConfig.getBaseUrl(),
            sendPhotoPath,
            Collections.emptyList()
        ))
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(BodyInserters.fromMultipartData(buildMultipartData(
            Arrays.asList(
                Pair.of("chat_id", telegramConfig.getChatId())
            ),
            Arrays.asList(
                Pair.of("photo", filePath)
            ))
            .build())
        );
  }

}
