package com.fox.shop.client.bot.service;

import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.repository.TgChatMessageHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class TgChatMessageHistoryServiceImpl {

  private final TgChatMessageHistoryRepository tgChatMessageHistoryRepository;
  private final TelegramApiMediator telegramApiMediator;

  public TgChatMessageHistoryServiceImpl(
      final TgChatMessageHistoryRepository tgChatMessageHistoryRepository,
      final TelegramApiMediator telegramApiMediator
  ) {
    this.tgChatMessageHistoryRepository = tgChatMessageHistoryRepository;
    this.telegramApiMediator = telegramApiMediator;
  }




}
