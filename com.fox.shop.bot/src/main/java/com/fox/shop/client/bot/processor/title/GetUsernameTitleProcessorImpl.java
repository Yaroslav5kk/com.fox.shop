package com.fox.shop.client.bot.processor.title;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.title.i.TgCommandTitleProcessorFather;
import com.fox.shop.client.bot.ui.view.StartViewer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@CommandProcessorComponent
public class GetUsernameTitleProcessorImpl implements TgCommandTitleProcessorFather {
  private final TelegramApiMediator telegramApiMediator;

  public GetUsernameTitleProcessorImpl(
      final TelegramApiMediator telegramApiMediator
  ) {
    this.telegramApiMediator = telegramApiMediator;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    telegramApiMediator.addMessage(incomingCommand.getUserId(), getName(incomingCommand.getChatId()));
  }

  private SendMessage getName(
      final long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setParseMode("HTML");
    result.setText(StartViewer.setName());
    return result;
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.GET_USERNAME_TITLE;
  }
}
