package com.fox.shop.storage.repository;

import com.fox.shop.storage.entity.FileInfoEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FileInfoRepository extends ReactiveMongoRepository<FileInfoEntity, String> {

  Mono<FileInfoEntity> getByBaseId(int baseId);

  Mono<FileInfoEntity> getByBaseProductId(long productId);


  Mono<FileInfoEntity> getByBaseProductGroupId(long productGroupId);
}
