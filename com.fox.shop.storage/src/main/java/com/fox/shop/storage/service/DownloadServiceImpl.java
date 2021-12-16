package com.fox.shop.storage.service;

import com.fox.shop.storage.entity.FileInfoEntity;
import com.fox.shop.storage.repository.FileInfoRepository;
import com.fox.shop.storage.service.i.DownloadService;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DownloadServiceImpl implements DownloadService {

  private final FileInfoRepository fileInfoRepository;

  public DownloadServiceImpl(
      final FileInfoRepository fileInfoRepository
  ) {
    this.fileInfoRepository = fileInfoRepository;
  }

  @Override
  public Mono<Resource> downloadById(
      final String id
  ) {
    return fileInfoRepository.findById(id).map(FileInfoEntity::getFilePath).map(PathResource::new);
  }
}
