package com.fox.shop.storage.service.i;

import com.fox.shop.storage.response.GeneralResponse;
import reactor.core.publisher.Mono;

public interface FileInfoService {
  Mono<GeneralResponse<String>> getTelegramIdByBaseId(
      int baseId
  );

  Mono<GeneralResponse<String>> getTelegramIdById(
          String id
  );
}
