package com.fox.shop.notify.bot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user_info")
public class UserInfoEntity {
  @Id
  @Field("id_on_base")
  private long userIdOnBase;
  @Field("id_on_telegram")
  private long userIdOnTelegram;
  @Field("chat_id")
  private String chatId;

  public long getUserIdOnBase() {
    return userIdOnBase;
  }

  public void setUserIdOnBase(long userIdOnBase) {
    this.userIdOnBase = userIdOnBase;
  }

  public long getUserIdOnTelegram() {
    return userIdOnTelegram;
  }

  public void setUserIdOnTelegram(long userIdOnTelegram) {
    this.userIdOnTelegram = userIdOnTelegram;
  }

  public String getChatId() {
    return chatId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }
}
