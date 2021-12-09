package com.fox.shop.client.bot.model.response;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public class SendMediaGroupResponse {
    private boolean ok;
    private List<Message> result;

    public SendMediaGroupResponse() {
    }

    public SendMediaGroupResponse(boolean ok, List<Message> result) {
        this.ok = ok;
        this.result = result;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<Message> getResult() {
        return result;
    }

    public void setResult(List<Message> result) {
        this.result = result;
    }
}
