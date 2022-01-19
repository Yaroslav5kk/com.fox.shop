package com.fox.shop.client.bot.processor.title;

import com.fox.shop.client.bot.annotation.CommandProcessorComponent;
import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.PriceApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.processor.title.i.TgCommandTitleProcessorFather;
import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
import com.fox.shop.client.bot.ui.generate.keyboard.i.ProductIKeyboardGenerator;
import com.fox.shop.client.bot.ui.view.ProductViewer;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.response.PageResponse;

@CommandProcessorComponent
public class ProductByGroupTitleProcessorImpl implements TgCommandTitleProcessorFather {
  private final TelegramApiMediator telegramApiMediator;
  private final StorageApiClient storageApiClient;
  private final BaseApiClient baseApiClient;
  private final PriceApiClient priceApiClient;
  private final UserModelDataContext userModelDataContext;
  private final PaginationMessageGenerator paginationMessageGenerator;
  private final ProductIKeyboardGenerator productIKeyboardGenerator;

  public ProductByGroupTitleProcessorImpl(
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
    allProductByGroup(incomingCommand);
  }

  private void allProductByGroup(
          final TgIncomingCommandModel incomingCommand
  ) {
    final int userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    final long groupId = Long.valueOf(incomingCommand.getParam0());
    userModelDataContext.productGroupId(userId, groupId);
    PageResponse<ProductModel> baseResponse = baseApiClient.productsByGroup(
            userId,
            groupId,
            null
    );
    for (final ProductModel productModel : baseResponse.getContent()) {
      telegramApiMediator.
              addMessage(userId, product(
                      chatId,
                      productModel,
                      storageApiClient.getTelegramIdById(productModel.getMainImageStorageId())
              ));
    }
    if (!baseResponse.isLast())
      telegramApiMediator.addMessage(userId, paginationMessageGenerator.pagination(chatId, CommandData.PRODUCTS_BY_GROUP + " " + groupId));
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
    return CommandData.PRODUCTS_BY_GROUP;
  }
}





































