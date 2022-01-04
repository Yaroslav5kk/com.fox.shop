package com.fox.shop.storage.service.i;

import com.fox.shop.storage.protocol.response.FileInfoResponse;
import com.fox.shop.storage.protocol.response.GeneralResponse;
import com.fox.shop.storage.protocol.types.FileType;
import com.fox.shop.storage.protocol.types.TelegramHolderType;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.IOException;

public interface UploadService {
  Mono<GeneralResponse<FileInfoResponse>> uploadToTelegram(
      TelegramHolderType holderType,
      FileType fileType,
      FilePart file
  ) throws IOException;
}
