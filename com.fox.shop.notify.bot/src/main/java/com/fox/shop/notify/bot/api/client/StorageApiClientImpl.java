package com.fox.shop.notify.bot.api.client;

import com.fox.shop.notify.bot.api.client.i.FatherApiClient;
import com.fox.shop.notify.bot.api.client.i.StorageApiClient;
import com.fox.shop.notify.bot.api.factory.i.StorageRequestFactory;
import com.fox.shop.storage.response.GeneralResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StorageApiClientImpl implements FatherApiClient, StorageApiClient {


  private final WebClient webClient;
  private final StorageRequestFactory storageRequestFactory;

  public StorageApiClientImpl(
          final StorageRequestFactory storageRequestFactory
  ) {
    this.storageRequestFactory = storageRequestFactory;
    webClient = WebClient.create();
  }

  @Override
  public Mono<String> getTelegramIdByBaseId(final long imageId) {
    return storageRequestFactory.getTelegramIdByBaseId(imageId, webClient).retrieve().bodyToMono(GeneralResponse.class)
            .map(generalResponse -> (String) generalResponse.getResult());
  }
}
