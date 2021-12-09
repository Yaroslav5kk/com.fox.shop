package com.fox.menu.bot.merchant.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "waiter_info")
public class WaiterInfoEntity {
    @Id
    @Field(name = "base_waiter_id")
    private long baseWaiterId;
    @Field(name = "telegram_id")
    private int telegramId;
    @Field(name = "chat_id")
    private long chatId;

    public long getBaseWaiterId() {
        return baseWaiterId;
    }

    public void setBaseWaiterId(long baseWaiterId) {
        this.baseWaiterId = baseWaiterId;
    }

    public int getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(int telegramId) {
        this.telegramId = telegramId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }
}
