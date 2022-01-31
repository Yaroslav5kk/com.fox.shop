package com.fox.shop.client.bot.service;

import com.fox.shop.client.bot.entity.TgChatMessageHistoryEntity;
import com.fox.shop.client.bot.repository.TgChatMessageHistoryRepository;
import com.fox.shop.client.bot.service.i.TgChatMessageHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TgChatMessageHistoryServiceImpl implements TgChatMessageHistoryService {

  private final TgChatMessageHistoryRepository tgChatMessageHistoryRepository;

  public TgChatMessageHistoryServiceImpl(
          final TgChatMessageHistoryRepository tgChatMessageHistoryRepository
  ) {
    this.tgChatMessageHistoryRepository = tgChatMessageHistoryRepository;
  }

  @Override
  public List<TgChatMessageHistoryEntity> getByUserId(final long userId) {
    return tgChatMessageHistoryRepository.findAllByUserId(userId);
  }

  @Override
  public void deleteAll(final List<TgChatMessageHistoryEntity> chatMessageHistory) {
    tgChatMessageHistoryRepository.deleteAll(chatMessageHistory);
  }


  @Override
  public void save(final TgChatMessageHistoryEntity toSave) {
    tgChatMessageHistoryRepository.save(toSave);
  }

  @Override
  public void saveAll(final List<TgChatMessageHistoryEntity> toSave) {
    tgChatMessageHistoryRepository.saveAll(toSave);
  }

}
