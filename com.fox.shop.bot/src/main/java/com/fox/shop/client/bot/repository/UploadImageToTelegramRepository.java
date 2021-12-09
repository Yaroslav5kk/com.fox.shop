package com.fox.shop.client.bot.repository;

import com.fox.shop.client.bot.entity.UploadImageToTelegramEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadImageToTelegramRepository extends MongoRepository<UploadImageToTelegramEntity, Long> {

    UploadImageToTelegramEntity findByFileIdOnTelegram(String id);
}
