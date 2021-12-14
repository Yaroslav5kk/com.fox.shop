package com.fox.shop.storage.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier("notify")
public class NotifyTelegramConfig implements TelegramConfig {
  @Value("${telegram.bot.notify.base-url}")
  private String baseUrl;
  @Value("${telegram.bot.notify.chat-id}")
  private String chatId;

  @Override
  public String getBaseUrl() {
    return baseUrl;
  }

  @Override
  public String getChatId() {
    return chatId;
  }
}
