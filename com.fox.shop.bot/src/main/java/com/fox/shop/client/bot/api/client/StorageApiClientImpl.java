package com.fox.shop.client.bot.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fox.shop.client.bot.api.client.i.FatherApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.factory.i.StorageRequestFactory;
import com.fox.shop.storage.protocol.response.GeneralResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StorageApiClientImpl implements FatherApiClient, StorageApiClient {

  private final CloseableHttpClient client;
  private final ObjectMapper objectMapper;
  private final StorageRequestFactory storageRequestFactory;

  public StorageApiClientImpl(
          final ObjectMapper objectMapper,
          final StorageRequestFactory storageRequestFactory
  ) {
    this.storageRequestFactory = storageRequestFactory;
    this.client = HttpClients.createDefault();
    this.objectMapper = objectMapper;
  }

  /*--------------------------------------------- telegram ----------------------------------------------------*/
  @Override
  public String getTelegramIdById(
          final String id
  ) {
    final Optional<GeneralResponse<String>> response = executeRequestAndExtractResponse(
            storageRequestFactory.getTelegramIdById(id),
            SimpleType.constructUnsafe(GeneralResponse.class)
    );
    return response.isPresent()
            ? response.get().getResult()
            : "";
  }

  @Override
  public CloseableHttpClient getClient() {
    return client;
  }


  @Override
  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }
}
