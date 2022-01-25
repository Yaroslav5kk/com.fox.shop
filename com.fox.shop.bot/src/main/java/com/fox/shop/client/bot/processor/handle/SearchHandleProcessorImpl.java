package com.fox.shop.client.bot.processor.handle;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.UserCommandStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.handle.i.TgCommandProcessorFather;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ProductIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ProductViewer;
import com.fox.shop.protocol.ProductModel;

@CommandProcessorComponent
public class SearchHandleProcessorImpl implements TgCommandProcessorFather {
  private final UserModelDataContext userModelDataContext;
  private final UserCommandStateContext userCommandStateContext;
  private final BaseApiClient baseApiClient;
  private final TelegramApiMediator telegramApiMediator;
  private final StorageApiClient storageApiClient;
  private final PriceApiClient priceApiClient;
  private final ProductIKeyboardGenerator productIKeyboardGenerator;

  public SearchHandleProcessorImpl(
      final UserModelDataContext userModelDataContext,
      final UserCommandStateContext userCommandStateContext,
      final BaseApiClient baseApiClient,
      final TelegramApiMediator telegramApiMediator,
      final StorageApiClient storageApiClient,
      final PriceApiClient priceApiClient,
      final ProductIKeyboardGenerator productIKeyboardGenerator
  ) {
    this.userModelDataContext = userModelDataContext;
    this.userCommandStateContext = userCommandStateContext;
    this.baseApiClient = baseApiClient;
    this.telegramApiMediator = telegramApiMediator;
    this.storageApiClient = storageApiClient;
    this.priceApiClient = priceApiClient;
    this.productIKeyboardGenerator = productIKeyboardGenerator;
  }

  @Override
  public void process(final TgIncomingCommandModel incomingCommand) {
    searchProductHandle(incomingCommand);
    userCommandStateContext.setup(incomingCommand.getUserId(), CommandData.SEARCH_HANDLE);
  }

  private void searchProductHandle(
      final TgIncomingCommandModel incomingCommand
  ) {
    baseApiClient.searchProductsByName(incomingCommand.getUserId(), incomingCommand.getInputData(), null)
        .getContent().forEach(productModel -> telegramApiMediator.addMessage(
            incomingCommand.getUserId(),
            product(
                incomingCommand.getChatId(),
                productModel,
                storageApiClient.getTelegramIdById(productModel.getMainImageStorageId())
            )
        ));
  }

  private SendPhotoFileIdRequest product(
      final long chatId,
      final ProductModel product,
      final String fileId
  ) {
    final SendPhotoFileIdRequest result = new SendPhotoFileIdRequest();
    result.setChatId(chatId);
    result.setCaption(ProductViewer.view(product, priceApiClient.getByProductId(product.getId(), 1).getPriceToView()));
    result.setParseMode("HTML");
    result.setReplyMarkup(productIKeyboardGenerator.product(product.getId()));
    result.setPhoto(fileId);
    return result;
  }

  @Override
  public CommandData getResponsibleCommand() {
    return CommandData.SEARCH_HANDLE;
  }
}
