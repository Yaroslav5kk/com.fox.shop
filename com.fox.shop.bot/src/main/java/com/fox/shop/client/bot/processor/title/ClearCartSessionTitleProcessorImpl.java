package com.fox.shop.client.bot.processor.title;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.title.i.TgCommandTitleProcessorFather;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ShoppingSessionIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ShoppingSessionViewer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@CommandProcessorComponent
public class ClearCartSessionTitleProcessorImpl implements TgCommandTitleProcessorFather {
  private final TelegramApiMediator telegramApiMediator;
  private final ShoppingCartApiClient shoppingCartApiClient;
  private final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator;
  private final PriceApiClient priceApiClient;
  private final StorageApiClient storageApiClient;
  private final BaseApiClient baseApiClient;
  private final UserModelDataContext userModelDataContext;

  public ClearCartSessionTitleProcessorImpl(
      final TelegramApiMediator telegramApiMediator,
      final ShoppingCartApiClient shoppingCartApiClient,
      final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator,
      final PriceApiClient priceApiClient,
      final StorageApiClient storageApiClient,
      final BaseApiClient baseApiClient,
      final UserModelDataContext userModelDataContext
  ) {
    this.telegramApiMediator = telegramApiMediator;
    this.shoppingCartApiClient = shoppingCartApiClient;
    this.shoppingSessionIKeyboardGenerator = shoppingSessionIKeyboardGenerator;
    this.priceApiClient = priceApiClient;
    this.storageApiClient = storageApiClient;
    this.baseApiClient = baseApiClient;
    this.userModelDataContext = userModelDataContext;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    shoppingCartApiClient.clearCartSessionByUser(incomingCommand.getUserId());
    userModelDataContext.clearAll(incomingCommand.getUserId());
    telegramApiMediator.addMessage(incomingCommand.getUserId(), successClearCartSession(incomingCommand));
  }


  private SendMessage successClearCartSession(
      final TgIncomingCommandModel incomingCommand
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(incomingCommand.getChatId());
    result.setReplyMarkup(shoppingSessionIKeyboardGenerator.startBack());
    result.setParseMode("HTML");
    result.setText(ShoppingSessionViewer.successClearSession());
    return result;
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.GET_PHONE_TITLE;
  }
}
