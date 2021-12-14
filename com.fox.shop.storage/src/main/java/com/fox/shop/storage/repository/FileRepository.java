package com.fox.shop.storage.repository;

import com.fox.shop.storage.entity.FileEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends ReactiveMongoRepository<FileEntity, String> {
}
