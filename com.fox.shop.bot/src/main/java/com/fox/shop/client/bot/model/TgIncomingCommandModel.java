package com.fox.shop.client.bot.model;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.utils.extractor.UpdateExtractor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

public class TgIncomingCommandModel {
  private int userId;
  private long chatId;
  private long messageId;
  private String inputData;
  private Optional<CommandData> command;
  private List<String> params;

  public static TgIncomingCommandModel of(final Update update) {
    final TgIncomingCommandModel result = new TgIncomingCommandModel();
    result.setChatId(UpdateExtractor.chatId(update));
    result.setUserId(UpdateExtractor.userId(update));
    result.setMessageId(UpdateExtractor.messageId(update));
    result.setInputData(UpdateExtractor.enteredText(update));
    result.setCommand(UpdateExtractor.command(update));
    result.setParams(UpdateExtractor.params(update));
    return result;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
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

  public String getParam0() {
    return params.get(0);
  }

  public String getParam1() {
    return params.get(1);
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
}




















