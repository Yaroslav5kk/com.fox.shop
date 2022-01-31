package com.fox.shop.client.bot.command.processor;

import com.fox.shop.client.bot.api.client.i.OrderingApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.events.TgRemoveMessagesApplicationEvent;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.ui.generate.i.OrderMessageGenerator;
import com.fox.shop.client.bot.command.processor.i.OrderCommandProcessor;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.ordering.protocol.types.OrderOriginType;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderCommandProcessorImpl implements OrderCommandProcessor {

  private final TgUserSessionContext tgUserSessionContext;
  private final OrderMessageGenerator orderMessageGenerator;
  private final ShoppingCartApiClient shoppingCartApiClient;
  private final OrderingApiClient orderingApiClient;
  private final TelegramApiMediator telegramApiMediator;
  private final ApplicationEventPublisher applicationEventPublisher;

  public OrderCommandProcessorImpl(
          final TgUserSessionContext tgUserSessionContext,
          final OrderMessageGenerator orderMessageGenerator,
          final ShoppingCartApiClient shoppingCartApiClient,
          final OrderingApiClient orderingApiClient,
          final TelegramApiMediator telegramApiMediator,
          final ApplicationEventPublisher applicationEventPublisher
  ) {
    this.tgUserSessionContext = tgUserSessionContext;
    this.orderMessageGenerator = orderMessageGenerator;
    this.shoppingCartApiClient = shoppingCartApiClient;
    this.orderingApiClient = orderingApiClient;
    this.telegramApiMediator = telegramApiMediator;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void makeOrderTitle(
          final TgIncomingCommandModel incomingCommand
  ) {
    applicationEventPublisher.publishEvent(new TgRemoveMessagesApplicationEvent(this, incomingCommand.getUserId()));
    final OrderOnCreateRequest request = new OrderOnCreateRequest();
    request.setTelegramUsername(incomingCommand.getUserName());
    request.setFirstname("to change first name, get from base service");
    request.setLastname("to change first name, get from base service");
    request.setShoppingCartSessionId(incomingCommand.getParam0AsLong());
    request.setOriginType(OrderOriginType.TELEGRAM);
    orderingApiClient.initOrder(request);
    telegramApiMediator.addMessage(orderMessageGenerator.makeOrderTitle(incomingCommand.getChatId()));
    shoppingCartApiClient.clearCartSessionByUser(incomingCommand.getUserId());
    tgUserSessionContext.clearAll(incomingCommand.getUserId());
  }

  @Override
  public void setOrderContactInfo(
          final TgIncomingCommandModel incomingCommand
  ) {
    tgUserSessionContext.getSession(incomingCommand.getUserId()).getOrderOnCreateRequest().setShoppingCartSessionId(incomingCommand.getInputDataAsLong());
    telegramApiMediator.addMessage(orderMessageGenerator.setOrderContactInfoTitle(incomingCommand.getChatId()));
  }

  @Override
  public void setOrderContactInfoFromProfileHandle(
          final TgIncomingCommandModel incomingCommand
  ) {
    telegramApiMediator.addMessage(orderMessageGenerator.setOrderContactInfoTitle(incomingCommand.getChatId()));
  }

}
