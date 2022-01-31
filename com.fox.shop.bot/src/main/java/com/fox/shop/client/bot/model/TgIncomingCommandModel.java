package com.fox.shop.client.bot.model;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.utils.extractor.UpdateExtractor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

public class TgIncomingCommandModel {
  private long userId;
  private String userName;
  private long chatId;
  private long messageId;
  private String inputData;
  private Optional<CommandData> command;
  private List<String> params;

  public static TgIncomingCommandModel of(final Update update) {
    final TgIncomingCommandModel result = new TgIncomingCommandModel();
    result.setChatId(UpdateExtractor.chatId(update));
    result.setUserName(UpdateExtractor.userName(update));
    result.setUserId(UpdateExtractor.userId(update));
    result.setMessageId(UpdateExtractor.messageId(update));
    result.setInputData(UpdateExtractor.enteredText(update));
    result.setCommand(UpdateExtractor.command(update));
    result.setParams(UpdateExtractor.params(update));
    return result;
  }

  public TgIncomingCommandModel userId(final long userId) {
    this.userId = userId;
    return this;
  }

  public TgIncomingCommandModel userName(final String userName) {
    this.userName = userName;
    return this;
  }

  public TgIncomingCommandModel chatId(final long chatId) {
    this.chatId = chatId;
    return this;
  }

  public long getInputDataAsLong() {
    return Long.parseLong(inputData);
  }

  public long getParam0AsLong() {
    return Long.parseLong(params.get(0));
  }

  public String getParam0() {
    return params.get(0);
  }

  public String getParam1() {
    return params.get(1);
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getChatId() {
    return chatId;
  }

  public void setChatId(long chatId) {
    this.chatId = chatId;
  }

  public String getInputData() {
    return inputData;
  }

  public void setInputData(String inputData) {
    this.inputData = inputData;
  }

  public List<String> getParams() {
    return params;
  }

  public void setParams(List<String> params) {
    this.params = params;
  }

  public Optional<CommandData> getCommand() {
    return command;
  }

  public void setCommand(Optional<CommandData> command) {
    this.command = command;
  }

  public long getMessageId() {
    return messageId;
  }

  public void setMessageId(long messageId) {
    this.messageId = messageId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}




















