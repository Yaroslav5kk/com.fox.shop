package com.fox.shop.client.bot.processor.title;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.title.i.TgCommandTitleProcessorFather;
import com.fox.shop.client.bot.ui.generate.i.StartMessageGeneratorMenu;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ReplyKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.StartIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.StartViewer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@CommandProcessorComponent
public class GetPhoneTitleProcessorImpl implements TgCommandTitleProcessorFather {
  private final ReplyKeyboardGenerator replyKeyboardGenerator;
  private final TelegramApiMediator telegramApiMediator;

  public GetPhoneTitleProcessorImpl(
      final ReplyKeyboardGenerator replyKeyboardGenerator,
      final TelegramApiMediator telegramApiMediator
  ) {
    this.replyKeyboardGenerator = replyKeyboardGenerator;
    this.telegramApiMediator = telegramApiMediator;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    telegramApiMediator.addMessage(incomingCommand.getUserId(), getPhone(incomingCommand.getChatId()));
  }

  private SendMessage getPhone(
      final long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setParseMode("HTML");
    result.setReplyMarkup(replyKeyboardGenerator.generateGetPhone());
    result.setText(StartViewer.setPhone());
    return result;
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.GET_PHONE;
  }
}
