package com.fox.shop.client.bot.service.interceptor.model;

import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.utils.extractor.UpdateExtractor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

public class TgCommandInterceptorModel {
  private int userId;
  private long chatId;
  private String data;
  private Optional<CommandData> command;
  private List<String> params;

  public static TgCommandInterceptorModel of(final Update update) {
    final TgCommandInterceptorModel result = new TgCommandInterceptorModel();
    result.setChatId(UpdateExtractor.chatId(update));
    result.setUserId(UpdateExtractor.userId(update));
    result.setData(UpdateExtractor.enteredText(update));
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

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
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
}




















