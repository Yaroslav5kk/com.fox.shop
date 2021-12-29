package com.fox.shop.storage.service;

import com.fox.shop.storage.entity.FileInfoEntity;
import com.fox.shop.storage.repository.FileInfoRepository;
import com.fox.shop.storage.response.GeneralResponse;
import com.fox.shop.storage.service.i.FileInfoService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FileInfoServiceImpl implements FileInfoService {

  private final FileInfoRepository fileInfoRepository;

  public FileInfoServiceImpl(
      final FileInfoRepository fileInfoRepository
  ) {
    this.fileInfoRepository = fileInfoRepository;
  }

  @Override
  public Mono<GeneralResponse<String>> getTelegramIdByBaseId(
      final int baseId
  ) {
    return fileInfoRepository.getByBaseId(baseId).map(FileInfoEntity::getTelegramFileId).map(GeneralResponse::new);
  }

}
