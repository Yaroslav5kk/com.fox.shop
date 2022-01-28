package com.fox.shop.client.bot.entity;

import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "chat_message_history")
public class TgChatMessageHistoryEntity {
  @Id
  private long chatId;
  private CommandData command;
  private long[] messagesIds;

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

  public long getChatId() {
    return chatId;
  }

  public void setChatId(long chatId) {
    this.chatId = chatId;
  }
}










