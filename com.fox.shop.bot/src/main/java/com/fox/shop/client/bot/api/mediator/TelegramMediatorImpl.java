package com.fox.shop.client.bot.api.mediator;

import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.model.request.SendMediaGroupRequest;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.model.request.TgDeleteMessageRequest;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.*;

@Service
public class TelegramMediatorImpl implements TelegramMediator {
  private final TelegramApiClient telegramApiClient;

  private Map<Integer, LinkedList<PartialBotApiMethod>> userIdMessagesToSent;

  public TelegramMediatorImpl(
          final TelegramApiClient telegramApiClient
  ) {
    this.telegramApiClient = telegramApiClient;
    this.userIdMessagesToSent = new HashMap<>();
  }

  @Override
  public List<Message> executeAll(final int userId) {
    final List<Message> result = new ArrayList<>();
    userIdMessagesToSent.get(userId).forEach(message -> {
      if (message instanceof SendMessage)
        result.add(telegramApiClient.sendMessage((SendMessage) message));
      else if (message instanceof SendPhotoFileIdRequest)
        result.add(telegramApiClient.sendPhoto((SendPhotoFileIdRequest) message));
      else if (message instanceof SendMediaGroupRequest)
        result.addAll(telegramApiClient.sendMediaGroup((SendMediaGroupRequest) message));
      else if (message instanceof EditMessageCaption)
        result.add(telegramApiClient.editMessageCaption((EditMessageCaption) message));
      else if (message instanceof EditMessageText)
        result.add(telegramApiClient.editMessageText((EditMessageText) message));
      else if (message instanceof TgDeleteMessageRequest)
        telegramApiClient.deleteMessage((TgDeleteMessageRequest) message);
    });
    return result;
  }

  @Override
  public void addMessage(
          final int userId,
          final PartialBotApiMethod message
  ) {
    if (!userIdMessagesToSent.containsKey(userId))
      userIdMessagesToSent.put(userId, new LinkedList<>());
    userIdMessagesToSent.get(userId).add(message);
  }

  @Override
  public void addMessages(
          final int userId,
          final List<PartialBotApiMethod> messages
  ) {
    if (!userIdMessagesToSent.containsKey(userId))
      userIdMessagesToSent.put(userId, new LinkedList<>());
    userIdMessagesToSent.get(userId).addAll(messages);
  }

 /* @Override
  public void addSendPhoto(
          final int userId,
          final SendPhotoFileIdRequest sendPhoto
  ) {
    if (!userIdSendPhoto.containsKey(userId))
      userIdSendPhoto.put(userId, new LinkedList<>());
    userIdSendPhoto.get(userId).add(sendPhoto);
  }

  @Override
  public void addSendPhoto(
          final int userId,
          final List<SendPhotoFileIdRequest> sendPhoto
  ) {
    if (!userIdSendPhoto.containsKey(userId))
      userIdSendPhoto.put(userId, new LinkedList<>());
    userIdSendPhoto.get(userId).addAll(sendPhoto);
  }

  @Override
  public void addEditMessageCaption(
          final int userId,
          final EditMessageCaption editMessageCaption
  ) {
    if (!userIdEditMessageCaption.containsKey(userId))
      userIdEditMessageCaption.put(userId, new LinkedList<>());
    userIdEditMessageCaption.get(userId).add(editMessageCaption);
  }

  @Override
  public void addEditMessageCaption(
          final int userId,
          final List<EditMessageCaption> editMessageCaptions
  ) {
    if (!userIdEditMessageCaption.containsKey(userId))
      userIdEditMessageCaption.put(userId, new LinkedList<>());
    userIdEditMessageCaption.get(userId).addAll(editMessageCaptions);
  }

  @Override
  public void addEditMessageText(
          final int userId,
          final EditMessageText editMessageText
  ) {
    if (!userIdEditMessageText.containsKey(userId))
      userIdEditMessageText.put(userId, new LinkedList<>());
    userIdEditMessageText.get(userId).add(editMessageText);
  }

  @Override
  public void addEditMessageText(
          final int userId,
          final List<EditMessageText> editMessageTexts
  ) {
    if (!userIdEditMessageText.containsKey(userId))
      userIdEditMessageText.put(userId, new LinkedList<>());
    userIdEditMessageText.get(userId).addAll(editMessageTexts);
  }*/
}

































