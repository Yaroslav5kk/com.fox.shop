package com.fox.shop.client.bot.processor.title;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.title.i.TgCommandTitleProcessorFather;
import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ProductIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ProductViewer;
import com.fox.shop.protocol.ProductModel;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;

@CommandProcessorComponent
public class ViewProductDescriptionTitleProcessorImpl implements TgCommandTitleProcessorFather {
  private final TelegramApiMediator telegramApiMediator;
  private final StorageApiClient storageApiClient;
  private final BaseApiClient baseApiClient;
  private final PriceApiClient priceApiClient;
  private final UserModelDataContext userModelDataContext;
  private final PaginationMessageGenerator paginationMessageGenerator;
  private final ProductIKeyboardGenerator productIKeyboardGenerator;

  public ViewProductDescriptionTitleProcessorImpl(
          final TelegramApiMediator telegramApiMediator,
          final StorageApiClient storageApiClient,
          final BaseApiClient baseApiClient,
          final PriceApiClient priceApiClient,
          final UserModelDataContext userModelDataContext,
          final PaginationMessageGenerator paginationMessageGenerator,
          final ProductIKeyboardGenerator productIKeyboardGenerator
  ) {
    this.telegramApiMediator = telegramApiMediator;
    this.storageApiClient = storageApiClient;
    this.baseApiClient = baseApiClient;
    this.priceApiClient = priceApiClient;
    this.userModelDataContext = userModelDataContext;
    this.paginationMessageGenerator = paginationMessageGenerator;
    this.productIKeyboardGenerator = productIKeyboardGenerator;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    telegramApiMediator.addMessage(
            incomingCommand.getUserId(),
            viewProductDescription(
                    incomingCommand.getChatId(),
                    incomingCommand.getMessageId(),
                    baseApiClient.productById(Long.valueOf(incomingCommand.getParam0()))
            )
    );

  }

  private EditMessageCaption viewProductDescription(
          final long chatId,
          final long messageId,
          final ProductModel product
  ) {
    final EditMessageCaption result = new EditMessageCaption();
    result.setChatId(String.valueOf(chatId));
    result.setParseMode("HTML");
    result.setMessageId((int) messageId);
    result.setCaption(ProductViewer.viewWithDescription(product, priceApiClient.getByProductId(product.getId(), 1).getPriceToView()));
    result.setReplyMarkup(productIKeyboardGenerator.onlyAddToCart(product.getId()));
    return result;
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.PRODUCTS_BY_GROUP;
  }
}





































