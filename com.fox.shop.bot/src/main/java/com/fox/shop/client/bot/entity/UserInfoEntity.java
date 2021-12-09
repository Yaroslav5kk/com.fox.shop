package com.fox.shop.client.bot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "user_info")
public class UserInfoEntity {
    @Id
    @Field(name = "base_user_id")
    private long baseUserId;
    @Field(name = "telegram_user_id")
    private long telegramUserId;
    @Field(name = "chat_id")
    private long chatId;
    @Field(name = "is_activated")
    private boolean isActivated;

    public long getTelegramUserId() {
        return telegramUserId;
    }

    public void setTelegramUserId(long telegramUserId) {
        this.telegramUserId = telegramUserId;
    }

    public long getBaseUserId() {
        return baseUserId;
    }

    public void setBaseUserId(long baseUserId) {
        this.baseUserId = baseUserId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}
