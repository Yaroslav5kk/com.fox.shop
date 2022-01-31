package com.fox.shop.client.bot.command.processor;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.client.i.StorageApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.events.TgRemoveMessagesApplicationEvent;
import com.fox.shop.client.bot.kafka.KafkaProducer;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.ui.generate.i.ProductMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.ShoppingCartMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.StartMessageGenerator;
import com.fox.shop.client.bot.command.processor.i.ShoppingCartCommandProcessor;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;
import com.fox.shop.shoppingcart.protocol.model.request.AddToCartRequest;
import com.fox.shop.shoppingcart.protocol.types.SessionOriginType;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartCommandProcessorImpl implements ShoppingCartCommandProcessor {

  private final ShoppingCartMessageGenerator shoppingCartMessageGenerator;
  private final ShoppingCartApiClient shoppingCartApiClient;
  private final TgUserSessionContext tgUserSessionContext;
  private final KafkaProducer kafkaProducer;
  private final ProductMessageGenerator productMessageGenerator;
  private final StartMessageGenerator startMessageGenerator;
  private final BaseApiClient baseApiClient;
  private final StorageApiClient storageApiClient;
  private final TelegramApiMediator telegramApiMediator;
  private final ApplicationEventPublisher applicationEventPublisher;

  public ShoppingCartCommandProcessorImpl(
          final ShoppingCartMessageGenerator shoppingCartMessageGenerator,
          final ShoppingCartApiClient shoppingCartApiClient,
          final TgUserSessionContext tgUserSessionContext,
          final KafkaProducer kafkaProducer,
          final ProductMessageGenerator productMessageGenerator,
          final StartMessageGenerator startMessageGenerator,
          final BaseApiClient baseApiClient,
          final StorageApiClient storageApiClient,
          final TelegramApiMediator telegramApiMediator,
          final ApplicationEventPublisher applicationEventPublisher
          ) {
    this.shoppingCartMessageGenerator = shoppingCartMessageGenerator;
    this.shoppingCartApiClient = shoppingCartApiClient;
    this.tgUserSessionContext = tgUserSessionContext;
    this.kafkaProducer = kafkaProducer;
    this.productMessageGenerator = productMessageGenerator;
    this.startMessageGenerator = startMessageGenerator;
    this.baseApiClient = baseApiClient;
    this.storageApiClient = storageApiClient;
    this.telegramApiMediator = telegramApiMediator;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void getCartSession(
          final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    applicationEventPublisher.publishEvent(new TgRemoveMessagesApplicationEvent(this, userId));
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
    tgUserSessionContext.clearAll(userId);
    applicationEventPublisher.publishEvent(new TgRemoveMessagesApplicationEvent(this, userId));
    telegramApiMediator.addMessage(shoppingCartMessageGenerator.
            successClearCartSession(chatId));
  }

  @Override
  public void addToCart(
          final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    applicationEventPublisher.publishEvent(new TgRemoveMessagesApplicationEvent(this, userId));
    tgUserSessionContext.getSession(userId).getCartItemOnCreateRequest().setProductId(incomingCommand.getParam0AsLong());
    telegramApiMediator.addMessage(shoppingCartMessageGenerator.setItemQuantityTitle(chatId));
    tgUserSessionContext.setupCommand(userId, CommandData.SET_ITEM_QUANTITY_ON_ADD_TO_CART_HANDLE);
  }

  @Override
  public void setCartItemQuantityOnUpdateTitle(
          final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    applicationEventPublisher.publishEvent(new TgRemoveMessagesApplicationEvent(this, userId));
    tgUserSessionContext.getSession(userId).setCartItemId(incomingCommand.getParam0AsLong());
    telegramApiMediator.addMessage(shoppingCartMessageGenerator.setItemQuantityTitle(chatId));
    tgUserSessionContext.setupCommand(userId, CommandData.SET_ITEM_QUANTITY_ON_UPDATE_CART_HANDLE);
  }

  @Override
  public void setCartItemQuantityOnUpdateHandle(
          final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    applicationEventPublisher.publishEvent(new TgRemoveMessagesApplicationEvent(this, userId));
    shoppingCartApiClient.updateCartItemQuantity(
            tgUserSessionContext.getSession(userId).getCartItemId(),
            (short) incomingCommand.getInputDataAsLong()
    );
    editCartSession(incomingCommand);
  }

  @Override
  public void setItemQuantityOnAddToCartHandle(
          final TgIncomingCommandModel incomingCommand
  ) {
    final long userId = incomingCommand.getUserId();
    final long chatId = incomingCommand.getChatId();
    applicationEventPublisher.publishEvent(new TgRemoveMessagesApplicationEvent(this, userId));
    tgUserSessionContext.getSession(userId).getCartItemOnCreateRequest().setQuantity((int) incomingCommand.getInputDataAsLong());
    final AddToCartRequest addToCartRequest = new AddToCartRequest();
    addToCartRequest.setUserId(userId);
    addToCartRequest.setCartItem(tgUserSessionContext.getSession(userId).getCartItemOnCreateRequest());
    addToCartRequest.setOriginType(SessionOriginType.TELEGRAM);
    final FullCartSessionModel fullCartSessionModel = kafkaProducer.addToCart(addToCartRequest);
    tgUserSessionContext.getSession(userId).setCartSessionId(fullCartSessionModel.getId());
    if (tgUserSessionContext.getSession(userId).getProductGroupId() != 0)
      baseApiClient.productsByGroup(userId, tgUserSessionContext.getSession(userId).getProductGroupId(), null).getContent().forEach(productModel ->
              telegramApiMediator.addMessage(productMessageGenerator.product(
                      chatId,
                      productModel,
                      storageApiClient.getTelegramIdById(productModel.getMainImageStorageId())
              )));
  }
}
