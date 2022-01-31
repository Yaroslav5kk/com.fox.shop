package com.fox.shop.client.bot.command;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.command.processor.i.StartCommandProcessor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class StartCommand extends AnonymizerCommand {

  private final StartCommandProcessor startScenarios;

  public StartCommand(
      final StartCommandProcessor startScenarios
  ) {
    super(CommandData.START.getValue(), CommandData.START.getDescription());

    this.startScenarios = startScenarios;
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    startScenarios.start(new TgIncomingCommandModel().chatId(chat.getId()).userId(user.getId()));
  }
}
