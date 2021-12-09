package com.fox.shop.client.bot.model.request;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;

public class SendMessageRequest implements Serializable {
    private String text;
    private Long chatId;

    public SendMessageRequest() {
    }

    public SendMessageRequest(final SendMessage sendMessage) {
        this(sendMessage.getText(), Long.valueOf(sendMessage.getChatId()));
    }

    public SendMessageRequest(final String text, final Long chatId) {
        this.text = text;
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return "SendMessageRequest{" +
                "text='" + text + '\'' +
                ", chatId=" + chatId +
                '}';
    }
}
