package com.fox.shop.client.bot.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TgDeleteMessageRequest extends PartialBotApiMethod {
    @JsonProperty("chat_id")
    private long chatId;
    @JsonProperty("message_id")
    private long messageId;

    public TgDeleteMessageRequest() {
    }

    public TgDeleteMessageRequest(long chatId, long messageId) {
        this.chatId = chatId;
        this.messageId = messageId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    @Override
    public Serializable deserializeResponse(String s) throws TelegramApiRequestException {
        return null;
    }
    @Override
    public void validate() throws TelegramApiValidationException {

    }
}
