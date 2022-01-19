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
public class TelegramApiMediatorImpl implements TelegramApiMediator {
  private final TelegramApiClient telegramApiClient;

  private Map<Integer, LinkedList<PartialBotApiMethod>> userIdMessagesToSent;

  public TelegramApiMediatorImpl(
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
  public <T extends PartialBotApiMethod>void addMessage(
          final int userId,
          final T message
  ) {
    if (!userIdMessagesToSent.containsKey(userId))
      userIdMessagesToSent.put(userId, new LinkedList<>());
    userIdMessagesToSent.get(userId).add(message);
  }

  @Override
  public void addMessages(
          final int userId,
          final List<? extends PartialBotApiMethod> messages
  ) {
    if (!userIdMessagesToSent.containsKey(userId))
      userIdMessagesToSent.put(userId, new LinkedList<>());
    userIdMessagesToSent.get(userId).addAll(messages);
  }

}

































