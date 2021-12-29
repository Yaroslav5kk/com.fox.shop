package com.fox.shop.storage.service;

import com.fox.shop.storage.api.client.i.TelegramApiClient;
import com.fox.shop.storage.config.TelegramConfig;
import com.fox.shop.storage.converter.FileInfoConverter;
import com.fox.shop.storage.entity.FileInfoEntity;
import com.fox.shop.storage.repository.FileInfoRepository;
import com.fox.shop.storage.response.FileInfoResponse;
import com.fox.shop.storage.response.GeneralResponse;
import com.fox.shop.storage.service.i.UploadService;
import com.fox.shop.storage.types.FileType;
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
  @Value("${storage.url}")
  private String storageUrl;

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
  public Mono<GeneralResponse<FileInfoResponse>> uploadToTelegram(
          final TelegramHolderType holderType,
          final FileType fileType,
          final FilePart file
  ) throws IOException {
    final File toSave = new File(storageDirPath + file.filename());
    file.transferTo(toSave).subscribe();
    final FileInfoEntity fileInfoBeforeTelegram = new FileInfoEntity()
            .filePath(toSave.getPath())
            .fileType(fileType)
            .setAndParseFromNameFormat(file.filename())
            .telegramHolderType(holderType)
            .storageProviderType(StorageProviderType.LOCAL);
    return Mono.just(fileInfoBeforeTelegram)
            .flatMap(fileInfoRepository::save)
            .doOnSuccess(fileInfo -> fileInfoBeforeTelegram.setId(fileInfo.getId()))
            .flatMap(fileInfo -> sendByFileType(storageUrl + fileInfo.getId(), fileInfo))
            .doOnSuccess(message -> fileInfoBeforeTelegram.setTelegramFileId(message.getPhoto().get(0).getFileId()))
            .flatMap(message -> fileInfoRepository.save(fileInfoBeforeTelegram))
            .then(Mono.just(new GeneralResponse(FileInfoConverter.entityToResponse(fileInfoBeforeTelegram))));
  }

  private Mono<Message> sendByFileType(
          final String fileUrl,
          final FileInfoEntity fileInfo
  ) {
    if (FileType.PHOTO.equals(fileInfo.getFileType()))
      return telegramApiClient.sendPhoto(fileUrl, extractTelegramConfig(fileInfo.getTelegramHolderType()));
    /*else if (FileType.ANIMATION.equals(fileInfo.getFileType()))
      return telegramApiClient.sendAnimation(fileUrl, extractTelegramConfig(fileInfo.getTelegramHolderType()));
    else if (FileType.VIDEO.equals(fileInfo.getFileType()))
      return telegramApiClient.sendVideo(fileUrl, extractTelegramConfig(fileInfo.getTelegramHolderType()));
    return Mono.empty();*/
    return null;
  }

  private TelegramConfig extractTelegramConfig(
          final TelegramHolderType type
  ) {
    if (TelegramHolderType.SHOP.equals(type))
      return shopTelegramConfig;
    else if (TelegramHolderType.NOTIFY.equals(type))
      return notifyTelegramConfig;
    return null;
  }
}
