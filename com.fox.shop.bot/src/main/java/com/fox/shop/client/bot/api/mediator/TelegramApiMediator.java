package com.fox.shop.client.bot.api.mediator;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

public interface TelegramApiMediator {
  List<Message> executeAll();

  <T extends PartialBotApiMethod>void addMessage(
          T message
  );

  void addMessages(
          List<? extends  PartialBotApiMethod> messages
  );
}
