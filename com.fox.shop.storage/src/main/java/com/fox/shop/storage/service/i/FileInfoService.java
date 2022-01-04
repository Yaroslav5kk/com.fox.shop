package com.fox.shop.storage.service.i;

import com.fox.shop.storage.protocol.response.GeneralResponse;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

public interface FileInfoService {
  Mono<GeneralResponse<String>> getTelegramId(
      @RequestParam(required = false) String id,
      @RequestParam(required = false) long baseProductId,
      @RequestParam(required = false) long baseProductGroupId
  );

  Mono<GeneralResponse<String>> getTelegramIdByBaseId(
      int baseId
  );

  Mono<GeneralResponse<String>> getTelegramIdById(
          String id
  );
}
