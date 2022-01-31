package com.fox.shop.client.bot.service.i;

import com.fox.shop.client.bot.entity.TgChatMessageHistoryEntity;

import java.util.List;

public interface TgChatMessageHistoryService {
  List<TgChatMessageHistoryEntity> getByUserId(long userId);

  void deleteAll(List<TgChatMessageHistoryEntity> chatMessageHistory);

  void save(TgChatMessageHistoryEntity toSave);

  void saveAll(List<TgChatMessageHistoryEntity> toSave);
}
