package com.fox.shop.client.bot.repository;

import com.fox.shop.client.bot.entity.UserInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfoEntity, Long> {

    UserInfoEntity findByTelegramUserId(long telegramUserId);

    boolean existsByTelegramUserId(long telegramUserId);
}
