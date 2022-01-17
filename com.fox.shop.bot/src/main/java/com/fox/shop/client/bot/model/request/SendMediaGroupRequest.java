package com.fox.shop.client.bot.model.request;

import com.fox.shop.client.bot.model.MediaModelTelegram;
import org.springframework.data.util.Pair;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class SendMediaGroupRequest extends PartialBotApiMethod {
    private long chatId;
    private List<MediaModelTelegram> media;
    private List<Pair<String, File>> nameFile;

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public List<MediaModelTelegram> getMedia() {
        return media;
    }

    public void setMedia(List<MediaModelTelegram> media) {
        this.media = media;
    }

    public List<Pair<String, File>> getNameFile() {
        return nameFile;
    }

    public void setNameFile(List<Pair<String, File>> nameFile) {
        this.nameFile = nameFile;
    }

    @Override
    public Serializable deserializeResponse(String s) throws TelegramApiRequestException {
        return null;
    }

    @Override
    public void validate() throws TelegramApiValidationException {

    }
}
