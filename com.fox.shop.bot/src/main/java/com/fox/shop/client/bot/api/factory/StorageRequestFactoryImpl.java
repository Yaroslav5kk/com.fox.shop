package com.fox.shop.client.bot.api.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fox.shop.client.bot.api.factory.i.FatherRequestFactory;
import com.fox.shop.client.bot.api.factory.i.StorageRequestFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class StorageRequestFactoryImpl implements FatherRequestFactory, StorageRequestFactory {

  @Value("${storage.api.url}")
  private String url;
  @Value("${storage.api.endpoint.get-telegram-id-by-id}")
  private String getTelegramIdById;

  private final ObjectMapper objectMapper;


  public StorageRequestFactoryImpl() {
    objectMapper = new ObjectMapper();
  }

  /*---------------------------------------------- telegramId -------------------------------------------------*/
  @Override
  public HttpUriRequest getTelegramIdById(
          final String id
  ) {
    final String fullUri = buildFullUri(
            url,
            getTelegramIdById + "/" + id,
            Collections.emptyList()
    );
    final HttpGet result = new HttpGet(fullUri);
    result.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());
    return result;
  }
}
