package com.fox.shop.client.bot.processor.title;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.title.i.TgCommandTitleProcessorFather;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ReplyKeyboardGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ShoppingSessionIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ShoppingSessionViewer;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@CommandProcessorComponent
public class GetCartSessionTitleProcessorImpl implements TgCommandTitleProcessorFather {
  private final ReplyKeyboardGenerator replyKeyboardGenerator;
  private final TelegramApiMediator telegramApiMediator;
  private final ShoppingCartApiClient shoppingCartApiClient;
  private final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator;
  private final PriceApiClient priceApiClient;

  public GetCartSessionTitleProcessorImpl(
      final ReplyKeyboardGenerator replyKeyboardGenerator,
      final TelegramApiMediator telegramApiMediator,
      final ShoppingCartApiClient shoppingCartApiClient,
      final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator,
      final PriceApiClient priceApiClient
  ) {
    this.replyKeyboardGenerator = replyKeyboardGenerator;
    this.telegramApiMediator = telegramApiMediator;
    this.shoppingCartApiClient = shoppingCartApiClient;
    this.shoppingSessionIKeyboardGenerator = shoppingSessionIKeyboardGenerator;
    this.priceApiClient = priceApiClient;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    getCartSessionProcess(incomingCommand);
  }

  private void getCartSessionProcess(
      final TgIncomingCommandModel incomingCommand
  ) {
    final FullCartSessionModel sessionModel = shoppingCartApiClient.getCartSessionByUser(incomingCommand.getUserId());
    if (sessionModel.getId() == 0)
      telegramApiMediator.addMessage(incomingCommand.getUserId(), emptyCartSession(incomingCommand));
    else
      telegramApiMediator.addMessage(incomingCommand.getUserId(), getCartSessionMessage(incomingCommand, sessionModel));
  }

  private SendMessage getCartSessionMessage(
      final TgIncomingCommandModel incomingCommand,
      final FullCartSessionModel cartSession
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(incomingCommand.getChatId());
    result.setReplyMarkup(shoppingSessionIKeyboardGenerator.getCartSession(cartSession.getId()));
    result.setParseMode("HTML");
    result.setText(ShoppingSessionViewer.view(cartSession, getSessionTotalPrice(cartSession)));
    return result;
  }

  private SendMessage emptyCartSession(
      final TgIncomingCommandModel incomingCommand
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(incomingCommand.getChatId());
    result.setReplyMarkup(shoppingSessionIKeyboardGenerator.startBack());
    result.setParseMode("HTML");
    result.setText(ShoppingSessionViewer.emptySession());
    return result;
  }

  private String getSessionTotalPrice(final FullCartSessionModel cartSession) {
    return cartSession.getItems()
        .stream()
        .map(fullCartItemModel -> priceApiClient.getByProductId(
            fullCartItemModel.getProductId(),
            fullCartItemModel.getQuantity()).getPrice())
        .reduce(Integer::sum).get().toString();
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.GET_PHONE_TITLE;
  }
}
