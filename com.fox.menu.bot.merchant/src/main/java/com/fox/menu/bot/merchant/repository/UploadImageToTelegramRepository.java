package com.fox.menu.bot.merchant.repository;

import com.fox.menu.bot.merchant.entity.UploadImageToTelegramEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UploadImageToTelegramRepository extends MongoRepository<UploadImageToTelegramEntity, Long> {

    UploadImageToTelegramEntity findByFileIdOnTelegram(String id);
}
