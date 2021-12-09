package com.fox.shop.client.bot.api.client.i;

import com.fox.shop.client.bot.model.request.SendMediaGroupRequest;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.model.response.DeleteMessageResponse;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface TelegramApiClient {

    Message sendPhoto(
            SendPhotoFileIdRequest sendPhoto
    );

    Message sendPhoto(
            SendPhoto sendPhoto
    );

    List<Message> sendMessage(List<SendMessage> sendMessage);

    Message sendMessage(SendMessage sendMessage);
    
    List<Message> sendMediaGroup(SendMediaGroupRequest sendMediaGroup);

    DeleteMessageResponse deleteMessage(long chatId, long messageId);

  Message editMessageCaption(EditMessageCaption editMessageCaption);

    Message editMessageText(EditMessageText editMessageText);
}
