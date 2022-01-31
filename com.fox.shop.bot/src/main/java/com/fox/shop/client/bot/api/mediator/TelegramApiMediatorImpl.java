package com.fox.shop.client.bot.api.mediator;

import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.entity.TgChatMessageHistoryEntity;
import com.fox.shop.client.bot.model.request.SendMediaGroupRequest;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.model.request.TgDeleteMessageRequest;
import com.fox.shop.client.bot.service.i.TgChatMessageHistoryService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TelegramApiMediatorImpl implements TelegramApiMediator {
  private final TelegramApiClient telegramApiClient;
  private final TgChatMessageHistoryService tgChatMessageHistoryService;

  private LinkedList<PartialBotApiMethod> userIdMessagesToSent;

  public TelegramApiMediatorImpl(
          final TelegramApiClient telegramApiClient,
          final TgChatMessageHistoryService tgChatMessageHistoryService
  ) {
    this.telegramApiClient = telegramApiClient;
    this.tgChatMessageHistoryService = tgChatMessageHistoryService;
    this.userIdMessagesToSent = new LinkedList<>();
  }

  @Override
  public List<Message> executeAll() {
    final List<Message> result = new ArrayList<>();
    userIdMessagesToSent.forEach(message -> {
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
    tgChatMessageHistoryService.save(TgChatMessageHistoryEntity.of(result));
    return result;
  }

  @Override
  public <T extends PartialBotApiMethod> void addMessage(
          final T message
  ) {
    execute(message);
  }

  @Override
  public void addMessages(
          final List<? extends PartialBotApiMethod> messages
  ) {
    messages.forEach(this::execute);
  }

  private List<Message> execute(final PartialBotApiMethod message) {
    final List<Message> result = new ArrayList<>();
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
    tgChatMessageHistoryService.save(TgChatMessageHistoryEntity.of(result));
    return result;
  }

}

































