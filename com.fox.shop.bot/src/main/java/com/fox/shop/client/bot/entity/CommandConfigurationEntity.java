package com.fox.shop.client.bot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "command_configuration")
public class CommandConfigurationEntity {
  @Id
  private String command;
  //often file is an image
  @Field(name = "pre_handle_file_id_on_telegram")
  private String preHandleFileIdOnTelegram;
  @Field(name = "pre_handle_message_text")
  private String preHandleMessageText;
  @Field(name = "is_to_sent_pre_handle_file")
  private boolean isToSentPreHandleFile;
  @Field(name = "post_handle_file_id_on_telegram")
  private String postHandleFileIdOnTelegram;
  @Field(name = "post_handle_message_text")
  private String postHandleMessageText;
  @Field(name = "is_to_sent_post_handle_file")
  private boolean isToSentPostHandleFile;

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public String getPreHandleFileIdOnTelegram() {
    return preHandleFileIdOnTelegram;
  }

  public void setPreHandleFileIdOnTelegram(String preHandleFileIdOnTelegram) {
    this.preHandleFileIdOnTelegram = preHandleFileIdOnTelegram;
  }

  public boolean isToSentPreHandleFile() {
    return isToSentPreHandleFile;
  }

  public void setToSentPreHandleFile(boolean toSentPreHandleFile) {
    isToSentPreHandleFile = toSentPreHandleFile;
  }

  public String getPostHandleFileIdOnTelegram() {
    return postHandleFileIdOnTelegram;
  }

  public void setPostHandleFileIdOnTelegram(String postHandleFileIdOnTelegram) {
    this.postHandleFileIdOnTelegram = postHandleFileIdOnTelegram;
  }

  public boolean isToSentPostHandleFile() {
    return isToSentPostHandleFile;
  }

  public void setToSentPostHandleFile(boolean toSentPostHandleFile) {
    isToSentPostHandleFile = toSentPostHandleFile;
  }

  public String getPreHandleMessageText() {
    return preHandleMessageText;
  }

  public void setPreHandleMessageText(String preHandleMessageText) {
    this.preHandleMessageText = preHandleMessageText;
  }

  public String getPostHandleMessageText() {
    return postHandleMessageText;
  }

  public void setPostHandleMessageText(String postHandleMessageText) {
    this.postHandleMessageText = postHandleMessageText;
  }
}
