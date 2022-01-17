package com.fox.shop.client.bot.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendPhotoFileIdRequest extends PartialBotApiMethod {
    @JsonProperty("chat_id")
    private long chatId;
    private String photo;
    private String caption;
    @JsonProperty("disable_notification")
    private boolean disableNotification;
    @JsonProperty("reply_to_message_id")
    private int replyToMessageId;
    @JsonProperty("reply_markup")
    private ReplyKeyboard replyMarkup;
    @JsonProperty("parse_mode")
    private String parseMode;

    public SendPhotoFileIdRequest() {
    }

    @Override
    public Serializable deserializeResponse(String s) throws TelegramApiRequestException {
        return null;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public boolean isDisableNotification() {
        return disableNotification;
    }

    public void setDisableNotification(boolean disableNotification) {
        this.disableNotification = disableNotification;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public int getReplyToMessageId() {
        return replyToMessageId;
    }

    public void setReplyToMessageId(int replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
    }

    public ReplyKeyboard getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(ReplyKeyboard replyMarkup) {
        this.replyMarkup = replyMarkup;
    }

    public String getParseMode() {
        return parseMode;
    }

    public void setParseMode(String parseMode) {
        this.parseMode = parseMode;
    }

    @Override
    public void validate() throws TelegramApiValidationException {

    }
}
