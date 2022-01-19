package com.fox.shop.client.bot.api.mediator;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface TelegramApiMediator {
  List<Message> executeAll(int userId);

  default Message execute(SendMessage sendMessage) {
    telegramApiClient.sendMessage(sendMessage);
  }

  void addMessage(
          int userId,
          PartialBotApiMethod message
  );

  void addMessages(
          int userId,
          List<PartialBotApiMethod> messages
  );
}
