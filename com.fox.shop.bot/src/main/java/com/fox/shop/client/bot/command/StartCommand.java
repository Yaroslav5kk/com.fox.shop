package com.fox.shop.client.bot.command;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.scenarios.i.StartScenarios;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Component
public class StartCommand extends AnonymizerCommand {

  private final StartScenarios startScenarios;
  private final UserHistoryContext userHistoryContext;

  public StartCommand(
      final StartScenarios startScenarios,
      final UserHistoryContext userHistoryContext
  ) {
    super(CommandData.START.getValue(), CommandData.START.getDescription(), userHistoryContext);

    this.startScenarios = startScenarios;
    this.userHistoryContext = userHistoryContext;
  }

  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
    startScenarios.start(new TgIncomingCommandModel().chatId(chat.getId()).userId(user.getId()));
    userHistoryContext.snapshot(user.getId(), CommandData.START.getValue());
  }
}
