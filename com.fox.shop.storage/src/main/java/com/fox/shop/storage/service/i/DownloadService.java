package com.fox.shop.storage.service.i;

import org.springframework.core.io.Resource;
import reactor.core.publisher.Mono;

public interface DownloadService {
  Mono<Resource> downloadById(
      String id
  );
}
