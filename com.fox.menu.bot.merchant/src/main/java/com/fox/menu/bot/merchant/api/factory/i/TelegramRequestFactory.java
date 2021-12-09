package com.fox.menu.bot.merchant.api.factory.i;

import com.fox.menu.bot.merchant.model.request.SendMediaGroupRequest;
import com.fox.menu.bot.merchant.model.request.SendPhotoFileIdRequest;
import org.apache.http.client.methods.HttpUriRequest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public interface TelegramRequestFactory {
    HttpUriRequest sendMessage(SendMessage sendMessage);

    HttpUriRequest sendPhoto(
            SendPhoto sendPhoto
    );

    HttpUriRequest sendPhoto(
            SendPhotoFileIdRequest sendPhoto
    );

    HttpUriRequest sendMediaGroup(SendMediaGroupRequest sendMediaGroup);

    HttpUriRequest deleteMessage(long chatId, long messageId);
}
