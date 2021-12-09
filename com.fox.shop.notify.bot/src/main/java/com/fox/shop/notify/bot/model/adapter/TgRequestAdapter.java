package com.fox.shop.notify.bot.model.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;

public class TgRequestAdapter {
  @JsonProperty("chat_id")
  private String chatId;
  @JsonProperty("text")
  private String text;
  @JsonProperty("parse_mode")
  private String parseMode;
  @JsonProperty("disable_web_page_preview")
  private Boolean disableWebPagePreview;
  @JsonProperty("disable_notification")
  private Boolean disableNotification;
  @JsonProperty("reply_to_message_id")
  private Integer replyToMessageId;
  @JsonProperty("reply_markup")
  @JsonDeserialize
  private ReplyKeyboard replyMarkup;
  @JsonProperty("entities")
  private List<MessageEntity> entities;
  @JsonProperty("allow_sending_without_reply")
  private Boolean allowSendingWithoutReply;
  private final SendMessage sendMessage;

  public TgRequestAdapter(final SendMessage sendMessage) {
    this.sendMessage = sendMessage;
  }


  public SendMessage getSendMessage() {
    return sendMessage;
  }
}
