package com.fox.shop.notify.bot.repository;

import com.fox.shop.notify.bot.entity.UploadImageToTelegramEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadImageToTelegramRepository extends ReactiveMongoRepository<UploadImageToTelegramEntity, Long> {

    UploadImageToTelegramEntity findByFileIdOnTelegram(String id);
}
