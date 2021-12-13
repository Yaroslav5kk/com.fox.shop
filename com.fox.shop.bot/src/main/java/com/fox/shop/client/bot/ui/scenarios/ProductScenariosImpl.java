package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserHistoryContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.ui.generate.i.PaginationMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.ProductMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.ProductScenarios;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class ProductScenariosImpl implements ProductScenarios {

  private final ProductMessageGenerator productMessageGenerator;
  private final UserDomainStateContext userDomainStateContext;
  private final UserProcessStateContext userProcessStateContext;
  private final UserModelDataContext userModelDataContext;
  private final UserHistoryContext userHistoryContext;
  private final TelegramApiClient telegramApiClient;
  private final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator;
  private final CommandConfigurationService commandConfigurationService;
  private final PaginationMessageGenerator paginationMessageGenerator;
  private final BaseApiClient baseApiClient;

  public ProductScenariosImpl(
      final ProductMessageGenerator productMessageGenerator,
      final UserDomainStateContext userDomainStateContext,
      final UserProcessStateContext userProcessStateContext,
      final UserModelDataContext userModelDataContext,
      final UserHistoryContext userHistoryContext,
      final TelegramApiClient telegramApiClient,
      final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator,
      final CommandConfigurationService commandConfigurationService,
      final PaginationMessageGenerator paginationMessageGenerator,
      final BaseApiClient baseApiClient
  ) {
    this.productMessageGenerator = productMessageGenerator;
    this.userDomainStateContext = userDomainStateContext;
    this.userProcessStateContext = userProcessStateContext;
    this.userModelDataContext = userModelDataContext;
    this.userHistoryContext = userHistoryContext;
    this.telegramApiClient = telegramApiClient;
    this.prePostCommandHandleMessageGenerator = prePostCommandHandleMessageGenerator;
    this.commandConfigurationService = commandConfigurationService;
    this.paginationMessageGenerator = paginationMessageGenerator;
    this.baseApiClient = baseApiClient;
  }

  @Override
  public void allByCategory(
      final long chatId,
      final User user,
      final long categoryId
  ) {
    preHandle(chatId, user.getId(), CommandData.PRODUCT_BY_CATEGORY.getValue());
    userProcessStateContext.free(user.getId());
    userDomainStateContext.start(user.getId());
    userModelDataContext.categoryId(user.getId(), categoryId);
    baseApiClient.productByCategory(categoryId).stream()
        .map(product -> productMessageGenerator.product(chatId, product, baseApiClient.mainImageByteByProduct(product.getId())))
        .peek(telegramApiClient::sendPhoto)
        .collect(Collectors.toList());
    telegramApiClient.sendMessage(productMessageGenerator.afterProductByCategory(chatId, user.getId(), userModelDataContext.getCartSessionId(user.getId())));
    postHandle(chatId, user.getId(), CommandData.PRODUCT_BY_CATEGORY.getValue());
  }

  @Override
  public void allProductByGroup(
      final long chatId,
      final int userId,
      final long groupId
  ) {
    preHandle(chatId, userId, CommandData.PRODUCTS_BY_GROUP.getValue());
    userModelDataContext.productGroupId(userId, groupId);
    baseApiClient.productsByGroup(groupId).forEach(productModel -> telegramApiClient.
        sendPhoto(productMessageGenerator.product(
            chatId,
            productModel,
            baseApiClient.mainImageByteByProduct(productModel.getId())
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
        .viewProductDescription(chatId, messageId, baseApiClient.productByIds(Arrays.asList(productId)).get(0))
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
