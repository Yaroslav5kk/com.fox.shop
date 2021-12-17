package com.fox.shop.storage.api.request.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TgSendPhotoFileIdRequest {
  @JsonProperty("chat_id")
  private String chatId;
  private String photo;

  public String getChatId() {
    return chatId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  @Override
  public String toString() {
    return "TgSendPhotoBinaryRequest{" +
        "chatId='" + chatId + '\'' +
        ", photo=" + photo +
        '}';
  }
}
