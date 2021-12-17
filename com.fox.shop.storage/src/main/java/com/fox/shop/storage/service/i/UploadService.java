package com.fox.shop.storage.service.i;

import com.fox.shop.storage.response.GeneralResponse;
import com.fox.shop.storage.types.FileType;
import com.fox.shop.storage.types.TelegramHolderType;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface UploadService {
  Mono<GeneralResponse<String>> uploadToTelegram(
      TelegramHolderType holderType,
      FileType fileType,
      FilePart file
  ) throws IOException;
}
