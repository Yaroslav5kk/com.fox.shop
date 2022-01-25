package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.kafka.KafkaProducer;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.ui.generate.i.ProductMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.ShoppingCartMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.StartMessageGeneratorMenu;
import com.fox.shop.client.bot.ui.scenarios.i.ShoppingCartScenarios;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.model.request.CartItemOnCreateRequest;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class ShoppingCartScenariosImpl implements ShoppingCartScenarios {

  private final ShoppingCartMessageGenerator shoppingCartMessageGenerator;
  private final ShoppingCartApiClient shoppingCartApiClient;
  private final UserModelDataContext userModelDataContext;
  private final KafkaProducer kafkaProducer;
  private final ProductMessageGenerator productMessageGenerator;
  private final StartMessageGeneratorMenu startMessageGeneratorMenu;
  private final BaseApiClient baseApiClient;
  private final StorageApiClient storageApiClient;
  private final TelegramApiMediator telegramApiMediator;

  public ShoppingCartScenariosImpl(
      final ShoppingCartMessageGenerator shoppingCartMessageGenerator,
      final ShoppingCartApiClient shoppingCartApiClient,
      final UserModelDataContext userModelDataContext,
      final KafkaProducer kafkaProducer,
      final ProductMessageGenerator productMessageGenerator,
      final StartMessageGeneratorMenu startMessageGeneratorMenu,
      final BaseApiClient baseApiClient,
      final StorageApiClient storageApiClient,
      final TelegramApiMediator telegramApiMediator
  ) {
    this.shoppingCartMessageGenerator = shoppingCartMessageGenerator;
    this.shoppingCartApiClient = shoppingCartApiClient;
    this.userModelDataContext = userModelDataContext;
    this.kafkaProducer = kafkaProducer;
    this.productMessageGenerator = productMessageGenerator;
    this.startMessageGeneratorMenu = startMessageGeneratorMenu;
    this.baseApiClient = baseApiClient;
    this.storageApiClient = storageApiClient;
    this.telegramApiMediator = telegramApiMediator;
  }

  @Override
  public void getCartSession(
      final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    final FullCartSessionModel sessionModel = shoppingCartApiClient.getCartSessionByUser(userId);
    if (sessionModel.getId() == 0)
      telegramApiMediator.addMessage(shoppingCartMessageGenerator.emptyCartSession(chatId));
    else
      telegramApiMediator.addMessage(shoppingCartMessageGenerator.
          getCartSession(chatId, sessionModel));
  }

  @Override
  public void editCartSession(
      final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    final FullCartSessionModel sessionModel = shoppingCartApiClient.getCartSessionByUser(userId);
    if (sessionModel.getId() == 0)
      telegramApiMediator.addMessage(shoppingCartMessageGenerator.emptyCartSession(chatId));
    else {
      telegramApiMediator.addMessages(shoppingCartMessageGenerator.
          editCartSessionTitle(chatId, sessionModel));
    }
  }

  @Override
  public void clearCartSession(
      final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    shoppingCartApiClient.clearCartSessionByUser(userId);
    userModelDataContext.clearAll(userId);
    telegramApiMediator.addMessage(shoppingCartMessageGenerator.
        successClearCartSession(chatId));
  }

  @Override
  public void addToCart(
      final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    userModelDataContext.cartItems(userId, new CartItemOnCreateRequest(incomingCommand.getInputDataAsLong()));
    userModelDataContext.productId(userId, incomingCommand.getInputDataAsLong());
    telegramApiMediator.addMessage(shoppingCartMessageGenerator.setItemQuantityTitle(chatId));
    return;
  }

  @Override
  public void setCartItemQuantityOnUpdateTitle(
      final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    userModelDataContext.cartItemId(userId, incomingCommand.getInputDataAsLong());
    telegramApiMediator.addMessage(shoppingCartMessageGenerator.setItemQuantityTitle(chatId));
  }

  @Override
  public void setCartItemQuantityOnUpdateHandle(
      final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    shoppingCartApiClient.updateCartItemQuantity(userModelDataContext.getCartItemIdFromRequest(userId), Short.valueOf(incomingCommand.getInputData()));
    editCartSession(incomingCommand);
  }

  @Override
  public void setItemQuantityForAddToCart(
      final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    userModelDataContext.getCartItem(userId).setQuantity(Integer.valueOf(incomingCommand.getInputData()));
    final AddToCartRequest addToCartRequest = new AddToCartRequest();
    addToCartRequest.setUserId(userId);
    addToCartRequest.setCartItem(userModelDataContext.getCartItem(userId));
    addToCartRequest.setOriginType(SessionOriginType.TELEGRAM);
    final FullCartSessionModel fullCartSessionModel = kafkaProducer.addToCart(addToCartRequest);
    userModelDataContext.cartSessionId(userId, fullCartSessionModel.getId());
    if (userModelDataContext.getProductGroupId(userId) != null)
      baseApiClient.productsByGroup(userId, userModelDataContext.getProductGroupId(userId), null).getContent().forEach(productModel ->
          telegramApiMediator.addMessage(productMessageGenerator.product(
              chatId,
              productModel,
              storageApiClient.getTelegramIdById(productModel.getMainImageStorageId())
          )));
    telegramApiMediator.addMessage(startMessageGeneratorMenu.base(chatId, userModelDataContext.getCartSessionId(userId)));
  }
}
