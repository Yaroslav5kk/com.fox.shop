package com.fox.shop.client.bot.interceptor;

import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.interceptor.i.FatherIncomingInterceptor;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.command.processor.i.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(4)
public class TgProcessCommandInterceptorImpl implements FatherIncomingInterceptor {

  private final StartCommandProcessor startScenarios;
  private final ProductCommandProcessor productScenarios;
  private final ShoppingCartCommandProcessor shoppingCartScenarios;
  private final OrderCommandProcessor orderScenarios;
  private final SearchCommandProcessor searchScenarios;
  private final TgUserSessionContext tgUserSessionContext;

  public TgProcessCommandInterceptorImpl(
      final StartCommandProcessor startScenarios,
      final ProductCommandProcessor productScenarios,
      final ShoppingCartCommandProcessor shoppingCartScenarios,
      final OrderCommandProcessor orderScenarios,
      final SearchCommandProcessor searchScenarios,
      final TgUserSessionContext tgUserSessionContext
  ) {
    this.startScenarios = startScenarios;
    this.productScenarios = productScenarios;
    this.shoppingCartScenarios = shoppingCartScenarios;
    this.orderScenarios = orderScenarios;
    this.searchScenarios = searchScenarios;
    this.tgUserSessionContext = tgUserSessionContext;
  }

  @Override
  public void interapt(
      final TgIncomingCommandModel incomingCommand
  ) {
    selectMenuSystems(incomingCommand);
    selectAuthorizeAuth(incomingCommand);
    selectShoppingCart(incomingCommand);
    selectOrder(incomingCommand);
    selectProduct(incomingCommand);
    selectSearch(incomingCommand);
  }

  private void selectMenuSystems(
      final TgIncomingCommandModel incomingCommand
  ) {
    switch (tgUserSessionContext.getCommand(incomingCommand.getUserId())) {
      case START:
        startScenarios.start(incomingCommand);
        break;
    }
  }

  private void selectAuthorizeAuth(
      final TgIncomingCommandModel incomingCommand
  ) {
    switch (tgUserSessionContext.getCommand(incomingCommand.getUserId())) {
      case GET_USERNAME_HANDLE:
        startScenarios.getNameHandle(incomingCommand);
        break;
      case GET_PHONE_HANDLE:
        startScenarios.getPhoneHandle(incomingCommand);
        break;
    }
  }

  private void selectShoppingCart(
      final TgIncomingCommandModel incomingCommand
  ) {
    switch (tgUserSessionContext.getCommand(incomingCommand.getUserId())) {
      case GET_CART_SESSION:
        shoppingCartScenarios.getCartSession(incomingCommand);
        break;
      case EDIT_CART_SESSION:
        shoppingCartScenarios.editCartSession(incomingCommand);
        break;
      case CLEAN_CART_SESSION:
        shoppingCartScenarios.clearCartSession(incomingCommand);
        break;
      case ADD_TO_CART:
        shoppingCartScenarios.addToCart(incomingCommand);
        break;
      case SET_ITEM_QUANTITY_ON_ADD_TO_CART_HANDLE:
        shoppingCartScenarios.setItemQuantityOnAddToCartHandle(incomingCommand);
        break;
      case SET_ITEM_QUANTITY_ON_UPDATE_CART_TITLE:
        shoppingCartScenarios.setCartItemQuantityOnUpdateTitle(incomingCommand);
        break;
      case SET_ITEM_QUANTITY_ON_UPDATE_CART_HANDLE:
        shoppingCartScenarios.setCartItemQuantityOnUpdateHandle(incomingCommand);
        break;
    }
  }

  private void selectOrder(
      final TgIncomingCommandModel incomingCommand
  ) {
    switch (tgUserSessionContext.getCommand(incomingCommand.getUserId())) {
      case MAKE_ORDER_TITLE:
        orderScenarios.makeOrderTitle(incomingCommand);
        break;
    }
  }

  private void selectProduct(
      final TgIncomingCommandModel incomingCommand
  ) {
    switch (tgUserSessionContext.getCommand(incomingCommand.getUserId())) {
      case PRODUCTS_BY_GROUP:
        productScenarios.productByGroup(incomingCommand);
        break;
      case VIEW_PRODUCT_DESCRIPTION:
        productScenarios.viewProductDescription(incomingCommand);
        break;
    }
  }

  private void selectSearch(
      final TgIncomingCommandModel incomingCommand
  ) {
    switch (tgUserSessionContext.getCommand(incomingCommand.getUserId())) {
      case SEARCH_TITLE:
        searchScenarios.searchTitle(incomingCommand);
        break;
      case SEARCH_HANDLE:
        searchScenarios.searchHandle(incomingCommand);
        break;
    }
  }


}










