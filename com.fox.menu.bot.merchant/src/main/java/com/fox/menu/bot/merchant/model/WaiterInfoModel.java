package com.fox.menu.bot.merchant.model;

import com.fox.menu.bot.merchant.entity.WaiterInfoEntity;

public class WaiterInfoModel {
    private long baseWaiterId;
    private int telegramId;
    private long chatId;

    public static WaiterInfoEntity toEntity(final WaiterInfoModel model) {
        final WaiterInfoEntity result = new WaiterInfoEntity();
        result.setBaseWaiterId(model.getBaseWaiterId());
        result.setTelegramId(model.getTelegramId());
        result.setChatId(model.getChatId());
        return result;
    }

    public static WaiterInfoModel fromEntity(final WaiterInfoEntity entity) {
        final WaiterInfoModel result = new WaiterInfoModel();
        result.setBaseWaiterId(entity.getBaseWaiterId());
        result.setTelegramId(entity.getTelegramId());
        result.setChatId(entity.getChatId());
        return result;
    }

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
