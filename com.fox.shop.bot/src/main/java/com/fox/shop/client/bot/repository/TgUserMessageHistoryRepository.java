package com.fox.shop.client.bot.repository;

import com.fox.shop.client.bot.entity.TgUserMessageHistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TgUserMessageHistoryRepository extends MongoRepository<TgUserMessageHistoryEntity, Long> {
}
