package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.kafka.KafkaProducer;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.ui.generate.i.*;
import com.fox.shop.client.bot.ui.scenarios.i.ShoppingCartScenarios;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class ShoppingCartScenariosImpl implements ShoppingCartScenarios {

  private final UserProcessStateContext userProcessStateContext;
  private final UserDomainStateContext userDomainStateContext;
  private final TelegramApiClient telegramApiClient;
  private final ShoppingCartMessageGenerator shoppingCartMessageGenerator;
  private final ShoppingCartApiClient shoppingCartApiClient;
  private final CommonMessageGenerator commonMessageGenerator;
  private final UserModelDataContext userModelDataContext;
  private final KafkaProducer kafkaProducer;
  private final ProductMessageGenerator productMessageGenerator;
  private final StartMessageGeneratorMenu startMessageGeneratorMenu;
  private final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator;
  private final CommandConfigurationService commandConfigurationService;
  private final BaseApiClient baseApiClient;

  public ShoppingCartScenariosImpl(
      final UserProcessStateContext userProcessStateContext,
      final UserDomainStateContext userDomainStateContext,
      final TelegramApiClient telegramApiClient,
      final ShoppingCartMessageGenerator shoppingCartMessageGenerator,
      final ShoppingCartApiClient shoppingCartApiClient,
      final CommonMessageGenerator commonMessageGenerator,
      final UserModelDataContext userModelDataContext,
      final KafkaProducer kafkaProducer,
      final ProductMessageGenerator productMessageGenerator,
      final StartMessageGeneratorMenu startMessageGeneratorMenu,
      final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator,
      final CommandConfigurationService commandConfigurationService,
      final BaseApiClient baseApiClient
  ) {
    this.userProcessStateContext = userProcessStateContext;
    this.userDomainStateContext = userDomainStateContext;
    this.telegramApiClient = telegramApiClient;
    this.shoppingCartMessageGenerator = shoppingCartMessageGenerator;
    this.shoppingCartApiClient = shoppingCartApiClient;
    this.commonMessageGenerator = commonMessageGenerator;
    this.userModelDataContext = userModelDataContext;
    this.kafkaProducer = kafkaProducer;
    this.productMessageGenerator = productMessageGenerator;
    this.startMessageGeneratorMenu = startMessageGeneratorMenu;
    this.prePostCommandHandleMessageGenerator = prePostCommandHandleMessageGenerator;
    this.commandConfigurationService = commandConfigurationService;
    this.baseApiClient = baseApiClient;
  }

  @Override
  public void getCartSession(
      final long chatId,
      final User user
  ) {
    preHandle(chatId, user.getId(), CommandData.GET_CART_SESSION.getValue());
    final FullCartSessionModel sessionModel = shoppingCartApiClient.getCartSessionByUser(user.getId());
    if (sessionModel.getId() == 0)
      telegramApiClient.sendMessage(shoppingCartMessageGenerator.emptyCartSession(chatId));
    else
      telegramApiClient.sendMessage(shoppingCartMessageGenerator.
          getCartSession(chatId, sessionModel));
    postHandle(chatId, user.getId(), CommandData.GET_CART_SESSION.getValue());
  }

  @Override
  public void editCartSession(
      final long chatId,
      final int userId
  ) {
    preHandle(chatId, userId, CommandData.EDIT_CART_SESSION.getValue());
    userDomainStateContext.setItemQuantityOnUpdateHandle(userId);
    final FullCartSessionModel sessionModel = shoppingCartApiClient.getCartSessionByUser(userId);
    if (sessionModel.getId() == 0)
      telegramApiClient.sendMessage(shoppingCartMessageGenerator.emptyCartSession(chatId));
    else {
      shoppingCartMessageGenerator.
          editCartSessionTitle(chatId, sessionModel).forEach(telegramApiClient::sendPhoto);
      telegramApiClient.sendMessage(shoppingCartMessageGenerator.beginBack(chatId));
    }
    postHandle(chatId, userId, CommandData.EDIT_CART_SESSION.getValue());
  }

  @Override
  public void clearCartSession(
      final long chatId,
      final User user
  ) {
    preHandle(chatId, user.getId(), CommandData.CLEAR_CART_SESSION.getValue());
    shoppingCartApiClient.clearCartSessionByUser(user.getId());
    userModelDataContext.cleanAll(user.getId());
    telegramApiClient.sendMessage(shoppingCartMessageGenerator.
        successClearCartSession(chatId));
    postHandle(chatId, user.getId(), CommandData.CLEAR_CART_SESSION.getValue());
  }

  @Override
  public void addToCart(
      final long chatId,
      final int userId,
      final long productId
  ) {
    preHandle(chatId, userId, CommandData.ADD_TO_CART.getValue());
    userDomainStateContext.setItemQuantityOnAddToCart(userId);
    userModelDataContext.cartItems(userId, new CartItemOnCreateRequest(productId));
    userModelDataContext.productId(userId, productId);
    telegramApiClient.sendMessage(shoppingCartMessageGenerator.setItemQuantityTitle(chatId));
    return;
  }

  @Override
  public void setCartItemQuantityOnUpdateTitle(
      final long chatId,
      final int userId,
      final long itemId
  ) {
    userModelDataContext.cartItemId(userId, itemId);
    userDomainStateContext.setItemQuantityOnUpdateHandle(userId);
    telegramApiClient.sendMessage(shoppingCartMessageGenerator.setItemQuantityTitle(chatId));
  }

  @Override
  public void setCartItemQuantityOnUpdateHandle(
      final long chatId,
      final int userId,
      final short quantity
  ) {
    shoppingCartApiClient.updateCartItemQuantity(userModelDataContext.getCartItemIdFromRequest(userId), quantity);
    editCartSession(chatId, userId);
  }

  @Override
  public void setItemQuantityForAddToCart(
      final long chatId,
      final int userId,
      final int quantity
  ) {
    preHandle(chatId, userId, CommandData.SET_ITEM_QUANTITY_ON_UPDATE_TITLE.getValue());
    userModelDataContext.getCartItem(userId).setQuantity(quantity);
    userDomainStateContext.addToCart(userId);
    userProcessStateContext.free(userId);
    final AddToCartRequest addToCartRequest = new AddToCartRequest();
    addToCartRequest.setUserId(userId);
    addToCartRequest.setCartItem(userModelDataContext.getCartItem(userId));
    addToCartRequest.setOriginType(SessionOriginType.TELEGRAM);
    final FullCartSessionModel fullCartSessionModel = kafkaProducer.addToCart(addToCartRequest);
    userModelDataContext.cartSessionId(userId, fullCartSessionModel.getId());
    if (userModelDataContext.getCategoryIdFromRequest(userId) != null)
      productMessageGenerator.productByCategory(userId, userModelDataContext.getCategoryIdFromRequest(userId)).
          forEach(telegramApiClient::sendPhoto);
    else if (userModelDataContext.getProductGroupId(userId) != null)
      baseApiClient.productsByGroup(userModelDataContext.getProductGroupId(userId)).forEach(productModel -> telegramApiClient.
          sendPhoto(productMessageGenerator.product(
              chatId,
              productModel,
              baseApiClient.mainImageByteByProduct(productModel.getId())
          )));
    telegramApiClient.sendMessage(startMessageGeneratorMenu.base(chatId,userModelDataContext.getCartSessionId(userId)));
    userModelDataContext.cleanAll(userId);
    postHandle(chatId, userId, CommandData.SET_ITEM_QUANTITY_ON_UPDATE_TITLE.getValue());
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
