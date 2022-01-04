package com.fox.shop.storage.service;

import com.fox.shop.storage.entity.FileInfoEntity;
import com.fox.shop.storage.protocol.response.GeneralResponse;
import com.fox.shop.storage.repository.FileInfoRepository;
import com.fox.shop.storage.service.i.FileInfoService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
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
  public Mono<GeneralResponse<String>> getTelegramId(
      @RequestParam(required = false) final String id,
      @RequestParam(required = false) final long baseProductId,
      @RequestParam(required = false) final long baseProductGroupId
  ) {
    if (StringUtils.hasText(id))
      return fileInfoRepository.findById(id).map(FileInfoEntity::getTelegramFileId).map(GeneralResponse::new);
    if (baseProductId != 0)
      return fileInfoRepository.getByBaseProductId(baseProductId).map(FileInfoEntity::getTelegramFileId).map(GeneralResponse::new);
    if (baseProductGroupId != 0)
      return fileInfoRepository.getByBaseProductGroupId(baseProductGroupId).map(FileInfoEntity::getTelegramFileId).map(GeneralResponse::new);
    return Mono.just(GeneralResponse.fail());
  }

  @Override
  public Mono<GeneralResponse<String>> getTelegramIdByBaseId(
      final int baseId
  ) {
    return fileInfoRepository.getByBaseId(baseId).map(FileInfoEntity::getTelegramFileId).map(GeneralResponse::new);
  }

  @Override
  public Mono<GeneralResponse<String>> getTelegramIdById(
      final String id
  ) {
    return fileInfoRepository.findById(id).map(FileInfoEntity::getTelegramFileId).map(GeneralResponse::new);
  }

}
