package com.fox.shop.storage.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier("shop")
public class ShopTelegramConfig implements TelegramConfig {
  @Value("${telegram.bot.shop.base-url}")
  private String baseUrl;
  @Value("${telegram.bot.shop.chat-id}")
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
