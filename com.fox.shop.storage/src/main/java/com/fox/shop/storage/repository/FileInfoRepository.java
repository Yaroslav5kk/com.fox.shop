package com.fox.shop.storage.repository;

import com.fox.shop.storage.entity.FileInfoEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends ReactiveMongoRepository<FileInfoEntity, String> {
}
