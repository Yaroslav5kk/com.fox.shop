package com.fox.shop.storage.service.i;

import reactor.core.publisher.Mono;

public interface FileInfoService {
  Mono<String> getTelegramIdByBaseId(
      String baseId
  );
}
