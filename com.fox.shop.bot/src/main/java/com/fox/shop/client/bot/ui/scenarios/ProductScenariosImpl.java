package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.events.TgRemoveMessagesApplicationEvent;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.ProductMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.ProductScenarios;
import com.fox.shop.protocol.ProductModel;
import com.fox.shop.protocol.response.PageResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ProductScenariosImpl implements ProductScenarios {

  private final ProductMessageGenerator productMessageGenerator;
  private final TgUserSessionContext tgUserSessionContext;
  private final PaginationMessageGenerator paginationMessageGenerator;
  private final BaseApiClient baseApiClient;
  private final StorageApiClient storageApiClient;
  private final TelegramApiMediator telegramApiMediator;
  private final ApplicationEventPublisher applicationEventPublisher;

  public ProductScenariosImpl(
          final ProductMessageGenerator productMessageGenerator,
          final TgUserSessionContext tgUserSessionContext,
          final PaginationMessageGenerator paginationMessageGenerator,
          final BaseApiClient baseApiClient,
          final StorageApiClient storageApiClient,
          final TelegramApiMediator telegramApiMediator,
          final ApplicationEventPublisher applicationEventPublisher
          ) {
    this.productMessageGenerator = productMessageGenerator;
    this.tgUserSessionContext = tgUserSessionContext;
    this.paginationMessageGenerator = paginationMessageGenerator;
    this.baseApiClient = baseApiClient;
    this.storageApiClient = storageApiClient;
    this.telegramApiMediator = telegramApiMediator;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void productByGroup(
          final TgIncomingCommandModel incomingCommand
  ) {
    applicationEventPublisher.publishEvent(new TgRemoveMessagesApplicationEvent(this, incomingCommand.getUserId()));
    tgUserSessionContext.getSession(incomingCommand.getUserId()).setProductGroupId(incomingCommand.getParam0AsLong());
    PageResponse<ProductModel> baseResponse = baseApiClient.productsByGroup(
            incomingCommand.getUserId(),
            incomingCommand.getParam0AsLong(),
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
      telegramApiMediator.addMessage(paginationMessageGenerator.pagination(incomingCommand.getChatId(), CommandData.PRODUCTS_BY_GROUP.getValue() + " " + incomingCommand.getParam0()));
  }

  @Override
  public void viewProductDescription(
          final TgIncomingCommandModel incomingCommand
  ) {
    telegramApiMediator.addMessage(productMessageGenerator
            .viewProductDescription(incomingCommand.getChatId(), incomingCommand.getMessageId(), baseApiClient.productById(incomingCommand.getParam0AsLong()))
    );

  }
}
