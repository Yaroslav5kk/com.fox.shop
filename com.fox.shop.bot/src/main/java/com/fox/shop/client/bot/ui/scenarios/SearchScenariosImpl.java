package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.ui.generate.i.GroupsMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.ProductMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.SearchMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.SearchScenarios;
import org.springframework.stereotype.Component;

@Component
public class SearchScenariosImpl implements SearchScenarios {

  private final UserDomainStateContext userDomainStateContext;
  private final UserProcessStateContext userProcessStateContext;
  private final SearchMessageGenerator searchMessageGenerator;
  private final TelegramApiClient telegramApiClient;
  private final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator;
  private final CommandConfigurationService commandConfigurationService;
  private final GroupsMessageGenerator groupsMessageGenerator;
  private final BaseApiClient baseApiClient;
  private final ProductMessageGenerator productMessageGenerator;
  private final StorageApiClient storageApiClient;

  public SearchScenariosImpl(
          final UserDomainStateContext userDomainStateContext,
          final UserProcessStateContext userProcessStateContext,
          final SearchMessageGenerator searchMessageGenerator,
          final TelegramApiClient telegramApiClient,
          final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator,
          final CommandConfigurationService commandConfigurationService,
          final GroupsMessageGenerator groupsMessageGenerator,
          final BaseApiClient baseApiClient,
          final ProductMessageGenerator productMessageGenerator,
          final StorageApiClient storageApiClient
  ) {
    this.userDomainStateContext = userDomainStateContext;
    this.userProcessStateContext = userProcessStateContext;
    this.searchMessageGenerator = searchMessageGenerator;
    this.telegramApiClient = telegramApiClient;
    this.prePostCommandHandleMessageGenerator = prePostCommandHandleMessageGenerator;
    this.commandConfigurationService = commandConfigurationService;
    this.groupsMessageGenerator = groupsMessageGenerator;
    this.baseApiClient = baseApiClient;
    this.productMessageGenerator = productMessageGenerator;
    this.storageApiClient = storageApiClient;
  }

  @Override
  public void searchTitle(
          final long chatId,
          final int userId
  ) {
    preHandle(chatId, userId, CommandData.SEARCH_TITLE.getValue());
    userDomainStateContext.searchTitle(userId);
    groupsMessageGenerator.allSearchProductGroups(chatId, userId).forEach(telegramApiClient::sendPhoto);
    telegramApiClient.sendMessage(searchMessageGenerator.searchTitle(chatId));
    postHandle(chatId, userId, CommandData.SEARCH_TITLE.getValue());
  }

  @Override
  public void searchProductTitle(
          final long chatId,
          final int userId
  ) {
    preHandle(chatId, userId, CommandData.SEARCH_PRODUCT.getValue());
    userDomainStateContext.searchProduct(userId);
    telegramApiClient.sendMessage(searchMessageGenerator.searchProductTitle(chatId));
    postHandle(chatId, userId, CommandData.SEARCH_PRODUCT.getValue());
  }

  @Override
  public void searchProductHandle(
          final long chatId,
          final int userId,
          final String toSearch
  ) {
    preHandle(chatId, userId, CommandData.SEARCH_PRODUCT.getValue());
    baseApiClient.searchProductsByName(userId, toSearch, null).getContent().forEach(productModel ->
            telegramApiClient.sendPhoto(productMessageGenerator.
                    product(chatId, productModel, storageApiClient.getTelegramIdById(productModel.getMainImageStorageId())))
    );
    //telegramApiClient.sendMessage(productMessageGenerator.beginBack(chatId));
    postHandle(chatId, userId, CommandData.SEARCH_PRODUCT.getValue());
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
