package com.fox.shop.client.bot.repository;

import com.fox.shop.client.bot.entity.TgChatMessageHistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TgChatMessageHistoryRepository extends MongoRepository<TgChatMessageHistoryEntity, Long> {
}
