package com.fox.shop.client.bot.interceptor;

import com.fox.shop.client.bot.command.CommandContainer;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.AnswerCallBackQuerySelector;
import com.fox.shop.client.bot.service.i.UserHistoryService;
import com.fox.shop.client.bot.interceptor.i.FatherIncomingInterceptor;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.ui.scenarios.i.*;
import com.fox.shop.client.bot.utils.extractor.UpdateExtractor;
import com.google.common.primitives.Longs;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
@Order(3)
public class TgSelectScenariosInterceptorImpl implements AnswerCallBackQuerySelector, FatherIncomingInterceptor {

  private final CommandContainer commandContainer;
  private final UserDomainStateContext userDomainStateContext;
  private final StartScenariosMenu startScenarios;
  private final UserProcessStateContext userProcessStateContext;
  private final ProductScenarios productScenarios;
  private final ShoppingCartScenarios shoppingCartScenarios;
  private final OrderScenarios orderScenarios;
  private final ResetScenarios resetScenarios;
  private final UserHistoryService userHistoryService;
  private final SearchScenarios searchScenarios;

  public TgSelectScenariosInterceptorImpl(
      final CommandContainer commandContainer,
      final UserDomainStateContext userDomainStateContext,
      final StartScenariosMenu startScenarios,
      final UserProcessStateContext userProcessStateContext,
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
    this.productScenarios = productScenarios;
    this.shoppingCartScenarios = shoppingCartScenarios;
    this.orderScenarios = orderScenarios;
    this.resetScenarios = resetScenarios;
    this.userHistoryService = userHistoryService;
    this.searchScenarios = searchScenarios;
  }

  @Override
  public void interapt(
      final TgIncomingCommandModel update
  ) {
    final UserDomainState actualDomainState = userDomainStateContext.current(update.getUserId());

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
    String command = UpdateExtractor.command(update).get().getValue();
    userHistoryService.snapshot(userId, command);
    command = userHistoryService.handle(userId, command);

    CommandData domainState = command.startsWith("/") && UserDomainState.isMain(CommandData.fromValue(command))
        ? UserDomainState.fromCommand(CommandData.fromValue(command))
        : userDomainStateContext.current(userId);
    userHistoryService.removeOldMessages(chatId, domainState);
    userDomainStateContext.put(userId, domainState);
    switch (domainState) {
      case START:
        startScenarios.start(chatId, user);
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
        productScenarios.viewProductDescription(chatId, UpdateExtractor.callBackQueryMessageId(update), Long.valueOf(callbackQueryData));
        break;
      case SEARCH_TITLE:
        searchScenarios.searchTitle(chatId, userId);
        break;
      case SEARCH_PRODUCT:
        searchScenarios.searchProductTitle(chatId, userId);
        break;
    }
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










