package com.fox.shop.client.bot.api.factory.i;

import com.fox.shop.client.bot.model.request.SendMediaGroupRequest;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import org.apache.http.client.methods.HttpUriRequest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface TelegramRequestFactory {
    HttpUriRequest sendMessage(SendMessage sendMessage);

    HttpUriRequest sendPhoto(
            SendPhotoFileIdRequest sendPhoto
    );

    HttpUriRequest sendMediaGroup(SendMediaGroupRequest sendMediaGroup);

    HttpUriRequest deleteMessage(long chatId, long messageId);

  HttpUriRequest editMessageCaption(EditMessageCaption editMessageCaption);

    HttpUriRequest editMessageText(EditMessageText editMessageText);
}
