package com.fox.shop.notify.bot.model.tg.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendPhotoFileIdRequest {
    private String chatId;
    private String photo;
    private String caption;
    private boolean disableNotification;
    private int replyToMessageId;
    private ReplyKeyboard replyMarkup;
    private String parseMode;

    public SendPhotoFileIdRequest() {
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

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
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
}
