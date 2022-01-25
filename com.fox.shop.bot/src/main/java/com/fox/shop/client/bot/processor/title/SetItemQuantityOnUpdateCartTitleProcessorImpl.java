package com.fox.shop.client.bot.processor.title;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.title.i.TgCommandTitleProcessorFather;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ShoppingSessionIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ShoppingSessionViewer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@CommandProcessorComponent
public class SetItemQuantityOnUpdateCartTitleProcessorImpl implements TgCommandTitleProcessorFather {
  private final TelegramApiMediator telegramApiMediator;
  private final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator;
  private final UserModelDataContext userModelDataContext;

  public SetItemQuantityOnUpdateCartTitleProcessorImpl(
      final TelegramApiMediator telegramApiMediator,
      final ShoppingSessionIKeyboardGenerator shoppingSessionIKeyboardGenerator,
      final UserModelDataContext userModelDataContext
  ) {
    this.telegramApiMediator = telegramApiMediator;
    this.shoppingSessionIKeyboardGenerator = shoppingSessionIKeyboardGenerator;
    this.userModelDataContext = userModelDataContext;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    userModelDataContext.cartItemId(incomingCommand.getUserId(), Long.valueOf(incomingCommand.getInputData()));
    telegramApiMediator.addMessage(incomingCommand.getUserId(), setItemQuantityTitle(incomingCommand.getChatId()));
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
    return CommandData.SET_ITEM_QUANTITY_ON_UPDATE_CART_TITLE;
  }
}
