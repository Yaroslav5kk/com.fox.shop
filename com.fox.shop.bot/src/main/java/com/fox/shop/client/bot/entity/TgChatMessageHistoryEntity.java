package com.fox.shop.client.bot.entity;

import com.fox.shop.client.bot.model.types.CommandData;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Document(collection = "chat_message_history")
public class TgChatMessageHistoryEntity {
  @Id
  private long id;
  private long chatId;
  private long userId;
  private CommandData command;
  private List<Long> messagesIds = new ArrayList<>();

  public static TgChatMessageHistoryEntity of(final List<Message> message) {
    if (CollectionUtils.isEmpty(message))
      return new TgChatMessageHistoryEntity();
    final TgChatMessageHistoryEntity result = new TgChatMessageHistoryEntity();
    result.setChatId(message.get(0).getChatId());
    result.setUserId(message.get(0).getChatId());
    result.setMessagesIds(message.stream().map(Message::getMessageId).map(Long::valueOf).collect(Collectors.toList()));
    return result;
  }

  public CommandData getCommand() {
    return command;
  }

  public void setCommand(CommandData command) {
    this.command = command;
  }

  public List<Long> getMessagesIds() {
    return messagesIds;
  }

  public void setMessagesIds(List<Long> messagesIds) {
    this.messagesIds = messagesIds;
  }

  public long getChatId() {
    return chatId;
  }

  public void setChatId(long chatId) {
    this.chatId = chatId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}










