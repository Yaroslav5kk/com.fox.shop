package com.fox.shop.notify.bot.api.factory.i;

import org.springframework.web.reactive.function.client.WebClient;

public interface StorageRequestFactory {
  /*----------------------------------------------images-------------------------------------------------*/
  WebClient.RequestHeadersSpec<?> getTelegramIdByBaseId(
          long id,
          WebClient webClient
  );
}
