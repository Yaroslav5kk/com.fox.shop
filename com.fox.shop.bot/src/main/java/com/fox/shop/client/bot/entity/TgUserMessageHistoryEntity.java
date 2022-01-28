package com.fox.shop.client.bot.entity;

import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "user_message_history")
public class TgUserMessageHistoryEntity {
  @Id
  private long userId;
  private CommandData command;
  private long[] messagesIds;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public CommandData getCommand() {
    return command;
  }

  public void setCommand(CommandData command) {
    this.command = command;
  }

  public long[] getMessagesIds() {
    return messagesIds;
  }

  public void setMessagesIds(long[] messagesIds) {
    this.messagesIds = messagesIds;
  }
}










