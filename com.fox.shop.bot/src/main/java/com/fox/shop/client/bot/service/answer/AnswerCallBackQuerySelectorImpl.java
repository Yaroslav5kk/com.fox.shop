package com.fox.shop.client.bot.service.answer;

import com.fox.shop.client.bot.command.CommandContainer;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.model.types.UserDomainState;
import com.fox.shop.client.bot.model.types.UserProcessState;
import com.fox.shop.client.bot.service.i.AnswerCallBackQuerySelector;
import com.fox.shop.client.bot.service.i.UserHistoryService;
import com.fox.shop.client.bot.ui.scenarios.i.*;
import com.fox.shop.client.bot.utils.extractor.UpdateExtractor;
import com.google.common.primitives.Longs;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
public class AnswerCallBackQuerySelectorImpl implements AnswerCallBackQuerySelector {

  private final CommandContainer commandContainer;
  private final UserDomainStateContext userDomainStateContext;
  private final StartScenariosMenu startScenarios;
  private final UserProcessStateContext userProcessStateContext;
  private final CategoryScenarios categoryScenarios;
  private final ProductScenarios productScenarios;
  private final ShoppingCartScenarios shoppingCartScenarios;
  private final OrderScenarios orderScenarios;
  private final ResetScenarios resetScenarios;
  private final UserHistoryService userHistoryService;
  private final SearchScenarios searchScenarios;

  public AnswerCallBackQuerySelectorImpl(
      final CommandContainer commandContainer,
      final UserDomainStateContext userDomainStateContext,
      final StartScenariosMenu startScenarios,
      final UserProcessStateContext userProcessStateContext,
      final CategoryScenarios categoryScenarios,
      final ProductScenarios productScenarios,
      final ShoppingCartScenarios shoppingCartScenarios,
      final OrderScenarios orderScenarios,
      final ResetScenarios resetScenarios,
      final UserHistoryService userHistoryService,
      final SearchScenarios searchScenarios
  ) {
    this.commandContainer = commandContainer;
    this.userDomainStateContext = userDomainStateContext;
    this.startScenarios = startScenarios;
    this.userProcessStateContext = userProcessStateContext;
    this.categoryScenarios = categoryScenarios;
    this.productScenarios = productScenarios;
    this.shoppingCartScenarios = shoppingCartScenarios;
    this.orderScenarios = orderScenarios;
    this.resetScenarios = resetScenarios;
    this.userHistoryService = userHistoryService;
    this.searchScenarios = searchScenarios;
  }

  @Override
  public void select(
      final Update update
  ) {
    final long chatId = UpdateExtractor.chatId(update);
    final User user = UpdateExtractor.user(update);
    final Integer userId = UpdateExtractor.userId(update);
    final String callbackQuery = UpdateExtractor.callbackQueryData(update);
    final long callbackQueryData = callbackQueryData(callbackQuery);
    String command = UpdateExtractor.command(update);
    userHistoryService.snapshot(userId, command);
    command = userHistoryService.handle(userId, command);

    if (!userProcessStateContext.isFree(userId)) {
      handleProcess(chatId, userId, Long.valueOf(callbackQueryData).intValue(), UpdateExtractor.enteredText(update));
      return;
    }

    UserDomainState domainState = command.startsWith("/") && UserDomainState.isMain(CommandData.fromValue(command))
        ? UserDomainState.fromCommand(CommandData.fromValue(command))
        : userDomainStateContext.current(userId);
    userHistoryService.removeOldMessages(chatId);
    userDomainStateContext.put(userId, domainState);
    switch (domainState) {
      case START:
        startScenarios.base(chatId, user);
        break;
      case RESET:
        resetScenarios.reset(chatId, userId);
        break;
      case PRODUCT_BY_CATEGORY:
        productScenarios.allByCategory(chatId, user, Long.valueOf(callbackQueryData));
        break;
      case GET_CART_SESSION:
        shoppingCartScenarios.getCartSession(chatId, user);
        break;
      case CLEAN_CART_SESSION:
        shoppingCartScenarios.clearCartSession(chatId, user);
        break;
      case EDIT_CART_SESSION:
        shoppingCartScenarios.editCartSession(chatId, userId);
        break;
      case SET_ITEM_QUANTITY_ON_ADD_TO_CART:
        shoppingCartScenarios.setItemQuantityForAddToCart(chatId, userId, (int) callbackQueryData);
        break;
      case SET_ITEM_QUANTITY_ON_UPDATE_TITLE:
        shoppingCartScenarios.setCartItemQuantityOnUpdateTitle(chatId, userId, (short) callbackQueryData);
        break;
      case SET_ITEM_QUANTITY_ON_UPDATE_HANDLE:
        shoppingCartScenarios.setCartItemQuantityOnUpdateHandle(chatId, userId, (short) callbackQueryData);
        break;
      case ADD_TO_CART:
        shoppingCartScenarios.addToCart(chatId, userId, callbackQueryData);
        break;
      case MAKE_ORDER_TITLE:
        orderScenarios.makeOrderTitle(chatId, user, callbackQueryData);
        break;
      case SET_ORDER_CONTACT_INFO_TITLE:
        orderScenarios.setOrderContactInfo(chatId, user, callbackQueryData);
        break;
      case PRODUCTS_BY_GROUP:
        productScenarios.allProductByGroup(chatId, userId, Long.valueOf(callbackQueryData));
        break;
      case VIEW_PRODUCT_DESCRIPTION:
        productScenarios.viewProductDescription(chatId, UpdateExtractor.messageId(update), Long.valueOf(callbackQueryData));
        break;
      case SEARCH_TITLE:
        searchScenarios.searchTitle(chatId, userId);
        break;
      case SEARCH_PRODUCT:
        searchScenarios.searchProductTitle(chatId, userId);
        break;
    }
  }

  public void handleProcess(
      final long chatId,
      final int userId,
      final int callbackQueryData,
      final String enteredText
  ) {
    final UserProcessState processState = userProcessStateContext.current(userId);

  }

  private long callbackQueryData(final String input) {
    final String[] divided = input.split(" ");
    if (divided.length <= 1 && Longs.tryParse(divided[0]) != null) {
      return Long.valueOf(input);
    }
    return divided.length > 1
        ? Long.valueOf(divided[1])
        : 0l;
  }
}










