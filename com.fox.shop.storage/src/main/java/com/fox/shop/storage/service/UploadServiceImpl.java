package com.fox.shop.storage.service;

import com.fox.shop.storage.api.client.i.TelegramApiClient;
import com.fox.shop.storage.config.TelegramConfig;
import com.fox.shop.storage.entity.FileInfoEntity;
import com.fox.shop.storage.repository.FileInfoRepository;
import com.fox.shop.storage.response.GeneralResponse;
import com.fox.shop.storage.service.i.UploadService;
import com.fox.shop.storage.types.StorageProviderType;
import com.fox.shop.storage.types.TelegramHolderType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@Service
public class UploadServiceImpl implements UploadService {

  @Value("${storage.dir-path}")
  private String storageDirPath;

  private final FileInfoRepository fileInfoRepository;
  private final TelegramConfig notifyTelegramConfig;
  private final TelegramConfig shopTelegramConfig;
  private final TelegramApiClient telegramApiClient;

  public UploadServiceImpl(
      final FileInfoRepository fileInfoRepository,
      @Qualifier("notify") final TelegramConfig notifyTelegramConfig,
      @Qualifier("shop") final TelegramConfig shopTelegramConfig,
      final TelegramApiClient telegramApiClient
  ) {
    this.fileInfoRepository = fileInfoRepository;
    this.notifyTelegramConfig = notifyTelegramConfig;
    this.shopTelegramConfig = shopTelegramConfig;
    this.telegramApiClient = telegramApiClient;
  }


  @Override
  public Mono<GeneralResponse<String>> uploadToTelegram(
      final TelegramHolderType holderType,
      final FilePart file
  ) throws IOException {
    final File toSave = new File(storageDirPath + file.filename());
    file.transferTo(toSave).subscribe();
    Mono<FileInfoEntity> fromTelegram = sendByHolderType(toSave.getPath(), holderType)
        .map(message -> message.getPhoto().get(0).getFileId())
        .map(telegramFileId -> new FileInfoEntity()
            .filePath(toSave.getPath())
            .telegramFileId(telegramFileId)
            .telegramHolderType(holderType)
            .storageProviderType(StorageProviderType.LOCAL));
    fromTelegram.subscribe();
    fromTelegram.doOnSuccess(fileInfoRepository::save).subscribe();
    return Mono.just(GeneralResponse.ok());
  }

  private Mono<Message> sendByHolderType(
      final String filepath,
      final TelegramHolderType type
  ) throws IOException {
    if (TelegramHolderType.SHOP.equals(type))
      return telegramApiClient.sendPhoto(filepath, shopTelegramConfig);
    else if (TelegramHolderType.NOTIFY.equals(type))
      return telegramApiClient.sendPhoto(filepath, notifyTelegramConfig);
    return Mono.empty();
  }
}
