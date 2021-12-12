package com.fox.shop.notify.bot.model.tg.response;

import org.telegram.telegrambots.meta.api.objects.Message;

public class GeneralTelegramResponse {
    private boolean ok;
    private Message result;

    public GeneralTelegramResponse() {
    }

    public GeneralTelegramResponse(boolean ok, Message result) {
        this.ok = ok;
        this.result = result;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public Message getResult() {
        return result;
    }

    public void setResult(Message result) {
        this.result = result;
    }
}
