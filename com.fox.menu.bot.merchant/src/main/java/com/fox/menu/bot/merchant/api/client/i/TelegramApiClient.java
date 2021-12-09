package com.fox.menu.bot.merchant.api.client.i;

import com.fox.menu.bot.merchant.model.request.SendMediaGroupRequest;
import com.fox.menu.bot.merchant.model.request.SendPhotoFileIdRequest;
import com.fox.menu.bot.merchant.model.response.DeleteMessageResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface TelegramApiClient {
    Message sendPhoto(
            SendPhotoFileIdRequest sendPhoto
    );

    Message sendPhoto(
            SendPhoto sendPhoto
    );

    List<Message> sendMessage(List<SendMessage> sendMessages);

    Message sendMessage(SendMessage sendMessage);

    Message sendMediaGroup(SendMediaGroupRequest sendMediaGroup);

    DeleteMessageResponse deleteMessage(long chatId, long messageId);
}
