package com.fox.shop.client.bot.processor.handle;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.handle.i.TgCommandProcessorFather;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ShoppingSessionIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ShoppingSessionViewer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@CommandProcessorComponent
public class SetItemQuantityOnUpdateCartHandleProcessorImpl implements TgCommandProcessorFather {
  private final TelegramApiMediator telegramApiMediator;
  private final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator;
  private final UserModelDataContext userModelDataContext;
  private final ShoppingCartApiClient shoppingCartApiClient;

  public SetItemQuantityOnUpdateCartHandleProcessorImpl(
      final TelegramApiMediator telegramApiMediator,
      final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator,
      final UserModelDataContext userModelDataContext,
      final ShoppingCartApiClient shoppingCartApiClient
  ) {
    this.telegramApiMediator = telegramApiMediator;
    this.shoppingSessionIKeyboardGenerator = shoppingSessionIKeyboardGenerator;
    this.userModelDataContext = userModelDataContext;
    this.shoppingCartApiClient = shoppingCartApiClient;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    shoppingCartApiClient.updateCartItemQuantity(userModelDataContext.getCartItemIdFromRequest(incomingCommand.getUserId()), Short.valueOf(incomingCommand.getInputData()));
  }

  private SendMessage setItemQuantityTitle(
      final long chatId
  ) {
    final SendMessage result = new SendMessage();
    result.setChatId(chatId);
    result.setParseMode("HTML");
    result.setText(ShoppingSessionViewer.setNewCartItemQuantityTitle());
    result.setReplyMarkup(shoppingSessionIKeyboardGenerator.setQuantity());
    return result;
  }


  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.SET_ITEM_QUANTITY_ON_UPDATE_CART_HANDLE;
  }
}
