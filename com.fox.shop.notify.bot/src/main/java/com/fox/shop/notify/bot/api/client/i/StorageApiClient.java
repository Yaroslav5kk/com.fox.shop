package com.fox.shop.notify.bot.api.client.i;

import reactor.core.publisher.Mono;

public interface StorageApiClient {
  Mono<String> getTelegramIdByBaseId(long imageId);
}
