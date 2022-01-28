package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.ProductMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.ProductScenarios;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.response.PageResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductScenariosImpl implements ProductScenarios {

  private final ProductMessageGenerator productMessageGenerator;
  private final TgUserSessionContext tgUserSessionContext;
  private final PaginationMessageGenerator paginationMessageGenerator;
  private final BaseApiClient baseApiClient;
  private final StorageApiClient storageApiClient;
  private final TelegramApiMediator telegramApiMediator;

  public ProductScenariosImpl(
      final ProductMessageGenerator productMessageGenerator,
      final TgUserSessionContext tgUserSessionContext,
      final PaginationMessageGenerator paginationMessageGenerator,
      final BaseApiClient baseApiClient,
      final StorageApiClient storageApiClient,
      final TelegramApiMediator telegramApiMediator
  ) {
    this.productMessageGenerator = productMessageGenerator;
    this.tgUserSessionContext = tgUserSessionContext;
    this.paginationMessageGenerator = paginationMessageGenerator;
    this.baseApiClient = baseApiClient;
    this.storageApiClient = storageApiClient;
    this.telegramApiMediator = telegramApiMediator;
  }

  @Override
  public void productByGroup(
      final TgIncomingCommandModel incomingCommand
  ) {
    tgUserSessionContext.getSession(incomingCommand.getUserId()).setProductGroupId(incomingCommand.getInputDataAsLong());
    PageResponse<ProductModel> baseResponse = baseApiClient.productsByGroup(
        incomingCommand.getUserId(),
        incomingCommand.getInputDataAsLong(),
        null
    );
    for (final ProductModel productModel : baseResponse.getContent()) {
      telegramApiMediator.
          addMessage(productMessageGenerator.product(
              incomingCommand.getChatId(),
              productModel,
              storageApiClient.getTelegramIdById(productModel.getMainImageStorageId())
          ));
    }
    if (!baseResponse.isLast())
      telegramApiMediator.addMessage(paginationMessageGenerator.pagination(incomingCommand.getChatId(), CommandData.PRODUCTS_BY_GROUP + " " + incomingCommand.getInputData()));
  }

  @Override
  public void viewProductDescription(
      final TgIncomingCommandModel incomingCommand
  ) {
    telegramApiMediator.addMessage(productMessageGenerator
        .viewProductDescription(incomingCommand.getChatId(), incomingCommand.getMessageId(), baseApiClient.productById(incomingCommand.getInputDataAsLong()))
    );

  }
}
