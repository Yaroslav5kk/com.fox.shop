package com.fox.shop.storage.api.request.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TgSendPhotoBinaryRequest {
  @JsonProperty("chat_id")
  private String chatId;
  private byte[] photo;

  public String getChatId() {
    return chatId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  public byte[] getPhoto() {
    return photo;
  }

  public void setPhoto(byte[] photo) {
    this.photo = photo;
  }

  @Override
  public String toString() {
    return "TgSendPhotoBinaryRequest{" +
        "chatId='" + chatId + '\'' +
        ", photo=" + Arrays.toString(photo) +
        '}';
  }
}
