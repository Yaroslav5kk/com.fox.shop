package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.UserCommandStateContext;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.GroupsMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.ProductMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.SearchMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.SearchScenarios;
import org.springframework.stereotype.Component;

@Component
public class SearchScenariosImpl implements SearchScenarios {

  private final SearchMessageGenerator searchMessageGenerator;
  private final GroupsMessageGenerator groupsMessageGenerator;
  private final BaseApiClient baseApiClient;
  private final ProductMessageGenerator productMessageGenerator;
  private final StorageApiClient storageApiClient;
  private final TelegramApiMediator telegramApiMediator;
  private final UserCommandStateContext userCommandStateContext;

  public SearchScenariosImpl(
      final SearchMessageGenerator searchMessageGenerator,
      final GroupsMessageGenerator groupsMessageGenerator,
      final BaseApiClient baseApiClient,
      final ProductMessageGenerator productMessageGenerator,
      final StorageApiClient storageApiClient,
      final TelegramApiMediator telegramApiMediator,
      final UserCommandStateContext userCommandStateContext
  ) {
    this.searchMessageGenerator = searchMessageGenerator;
    this.groupsMessageGenerator = groupsMessageGenerator;
    this.baseApiClient = baseApiClient;
    this.productMessageGenerator = productMessageGenerator;
    this.storageApiClient = storageApiClient;
    this.telegramApiMediator = telegramApiMediator;
    this.userCommandStateContext = userCommandStateContext;
  }

  @Override
  public void searchTitle(
      final TgIncomingCommandModel incomingCommand
  ) {
    telegramApiMediator.addMessages(groupsMessageGenerator.allSearchProductGroups(incomingCommand.getChatId(), incomingCommand.getUserId()));
    telegramApiMediator.addMessage(searchMessageGenerator.searchTitle(incomingCommand.getChatId()));
    userCommandStateContext.setup(incomingCommand.getUserId(), CommandData.SEARCH_HANDLE);
  }

  @Override
  public void searchHandle(
      final TgIncomingCommandModel incomingCommand
  ) {
    baseApiClient.searchProductsByName(incomingCommand.getUserId(), incomingCommand.getInputData(), null).getContent().forEach(productModel ->
        telegramApiMediator.addMessage(productMessageGenerator.
            product(incomingCommand.getChatId(), productModel, storageApiClient.getTelegramIdById(productModel.getMainImageStorageId())))
    );
  }

}















































