package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.*;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.ProductMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.ProductScenarios;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductScenariosImpl implements ProductScenarios {

  @Value("${content.product.pagination-size}")
  private int paginationSize;

  private final ProductMessageGenerator productMessageGenerator;
  private final UserDomainStateContext userDomainStateContext;
  private final UserProcessStateContext userProcessStateContext;
  private final UserModelDataContext userModelDataContext;
  private final PaginationDataContext paginationDataContext;
  private final UserHistoryContext userHistoryContext;
  private final TelegramApiClient telegramApiClient;
  private final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator;
  private final CommandConfigurationService commandConfigurationService;
  private final PaginationMessageGenerator paginationMessageGenerator;
  private final BaseApiClient baseApiClient;
  private final StorageApiClient storageApiClient;

  public ProductScenariosImpl(
          final ProductMessageGenerator productMessageGenerator,
          final UserDomainStateContext userDomainStateContext,
          final UserProcessStateContext userProcessStateContext,
          final UserModelDataContext userModelDataContext,
          final PaginationDataContext paginationDataContext,
          final UserHistoryContext userHistoryContext,
          final TelegramApiClient telegramApiClient,
          final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator,
          final CommandConfigurationService commandConfigurationService,
          final PaginationMessageGenerator paginationMessageGenerator,
          final BaseApiClient baseApiClient,
          final StorageApiClient storageApiClient
  ) {
    this.productMessageGenerator = productMessageGenerator;
    this.userDomainStateContext = userDomainStateContext;
    this.userProcessStateContext = userProcessStateContext;
    this.userModelDataContext = userModelDataContext;
    this.paginationDataContext = paginationDataContext;
    this.userHistoryContext = userHistoryContext;
    this.telegramApiClient = telegramApiClient;
    this.prePostCommandHandleMessageGenerator = prePostCommandHandleMessageGenerator;
    this.commandConfigurationService = commandConfigurationService;
    this.paginationMessageGenerator = paginationMessageGenerator;
    this.baseApiClient = baseApiClient;
    this.storageApiClient = storageApiClient;
  }

  @Override
  public void allProductByGroup(
          final long chatId,
          final int userId,
          final long groupId
  ) {
    preHandle(chatId, userId, CommandData.PRODUCTS_BY_GROUP.getValue());
    userModelDataContext.productGroupId(userId, groupId);
    baseApiClient.productsByGroup(
            groupId,
            PageRequest.of(paginationDataContext.getNextPage(userId), paginationSize)
    ).forEach(productModel -> telegramApiClient.
            sendPhoto(productMessageGenerator.product(
                    chatId,
                    productModel,
                    storageApiClient.getTelegramIdById(productModel.getMainImageStorageId())
            )));
    telegramApiClient.sendMessage(productMessageGenerator.beginBack(chatId));
    postHandle(chatId, userId, CommandData.PRODUCTS_BY_GROUP.getValue());
  }

  @Override
  public void viewProductDescription(
          final long chatId,
          final long messageId,
          final long productId
  ) {
    telegramApiClient.editMessageCaption(productMessageGenerator
            .viewProductDescription(chatId, messageId, baseApiClient.productsByIds(Arrays.asList(productId)).get(0))
    );

  }

  @Override
  public PrePostCommandHandleMessageGenerator getPrePostCommandHandleMessageGenerator() {
    return prePostCommandHandleMessageGenerator;
  }

  @Override
  public TelegramApiClient getTelegramApiClient() {
    return telegramApiClient;
  }

  @Override
  public CommandConfigurationService getCommandConfigurationService() {
    return commandConfigurationService;
  }
}
