package com.fox.shop.client.bot.api.factory.i;

import com.fox.shop.client.bot.model.request.SendMediaGroupRequest;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.squareup.okhttp.Request;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public interface TelegramRequestFactory {
    Request sendMessage(SendMessage sendMessage);

    Request sendPhoto(
            SendPhotoFileIdRequest sendPhoto
    );

    Request sendMediaGroup(SendMediaGroupRequest sendMediaGroup);

    Request deleteMessage(long chatId, long messageId);

  Request editMessageCaption(EditMessageCaption editMessageCaption);

    Request editMessageText(EditMessageText editMessageText);
}
