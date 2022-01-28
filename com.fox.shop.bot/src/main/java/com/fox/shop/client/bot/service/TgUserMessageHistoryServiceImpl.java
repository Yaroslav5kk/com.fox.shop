package com.fox.shop.client.bot.service;

import com.fox.shop.client.bot.repository.TgUserMessageHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TgUserMessageHistoryServiceImpl {

  private final TgUserMessageHistoryRepository tgUserMessageHistoryRepository;

  public TgUserMessageHistoryServiceImpl(
      final TgUserMessageHistoryRepository tgUserMessageHistoryRepository
  ) {
    this.tgUserMessageHistoryRepository = tgUserMessageHistoryRepository;
  }

  @Override
  public


}
