package com.fox.shop.storage.service;

import com.fox.shop.storage.entity.FileEntity;
import com.fox.shop.storage.repository.FileRepository;
import com.fox.shop.storage.service.i.FileInfoService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FileInfoServiceImpl implements FileInfoService {

  private final FileRepository fileRepository;

  public FileInfoServiceImpl(
      final FileRepository fileRepository
  ) {
    this.fileRepository = fileRepository;
  }

  @Override
  public Mono<String> getTelegramIdByBaseId(
      final String baseId
  ) {
    return fileRepository.findById(baseId).map(FileEntity::getTelegramFileId);
  }

}
