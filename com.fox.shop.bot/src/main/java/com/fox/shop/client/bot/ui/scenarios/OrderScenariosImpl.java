package com.fox.shop.client.bot.ui.scenarios;

import com.fox.shop.client.bot.api.client.i.BaseApiClient;
import com.fox.shop.client.bot.api.client.i.OrderingApiClient;
import com.fox.shop.client.bot.api.client.i.ShoppingCartApiClient;
import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.context.i.UserModelDataContext;
import com.fox.shop.client.bot.context.i.UserProcessStateContext;
import com.fox.shop.client.bot.kafka.KafkaProducer;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.service.i.CommandConfigurationService;
import com.fox.shop.client.bot.service.i.UserService;
import com.fox.shop.client.bot.ui.generate.i.OrderMessageGenerator;
import com.fox.shop.client.bot.ui.generate.i.PrePostCommandHandleMessageGenerator;
import com.fox.shop.client.bot.ui.scenarios.i.OrderScenarios;
import com.fox.shop.ordering.protocol.request.OrderOnCreateRequest;
import com.fox.shop.ordering.protocol.types.OrderOriginType;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class OrderScenariosImpl implements OrderScenarios {

    private final UserProcessStateContext userProcessStateContext;
    private final UserDomainStateContext userDomainStateContext;
    private final UserModelDataContext userModelDataContext;
    private final TelegramApiClient telegramApiClient;
    private final OrderMessageGenerator orderMessageGenerator;
    private final KafkaProducer kafkaProducer;
    private final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator;
    private final CommandConfigurationService commandConfigurationService;
    private final ShoppingCartApiClient shoppingCartApiClient;
    private final OrderingApiClient orderingApiClient;
    private BaseApiClient baseApiClient;
    private final UserService userService;

    public OrderScenariosImpl(
            final UserProcessStateContext userProcessStateContext,
            final UserDomainStateContext userDomainStateContext,
            final UserModelDataContext userModelDataContext,
            final TelegramApiClient telegramApiClient,
            final OrderMessageGenerator orderMessageGenerator,
            final KafkaProducer kafkaProducer,
            final PrePostCommandHandleMessageGenerator prePostCommandHandleMessageGenerator,
            final CommandConfigurationService commandConfigurationService,
            final ShoppingCartApiClient shoppingCartApiClient,
            final OrderingApiClient orderingApiClient,
            final BaseApiClient baseApiClient,
            final UserService userService
    ) {
        this.userProcessStateContext = userProcessStateContext;
        this.userDomainStateContext = userDomainStateContext;
        this.userModelDataContext = userModelDataContext;
        this.telegramApiClient = telegramApiClient;
        this.orderMessageGenerator = orderMessageGenerator;
        this.kafkaProducer = kafkaProducer;
        this.prePostCommandHandleMessageGenerator = prePostCommandHandleMessageGenerator;
        this.commandConfigurationService = commandConfigurationService;
        this.shoppingCartApiClient = shoppingCartApiClient;
        this.orderingApiClient = orderingApiClient;
        this.baseApiClient = baseApiClient;
        this.userService = userService;
    }

    @Override
    public void makeOrderTitle(
            final long chatId,
            final User user,
            final long cartSessionId
    ) {
        final OrderOnCreateRequest request = new OrderOnCreateRequest();
        request.setTelegramUsername(user.getUserName());
        request.setFirstname(user.getFirstName());
        request.setLastname(user.getLastName());
        request.setShoppingCartSessionId(cartSessionId);
        request.setOriginType(OrderOriginType.TELEGRAM);
        orderingApiClient.initOrder(request);
        telegramApiClient.sendPhoto(orderMessageGenerator.makeOrderTitle(chatId));
        shoppingCartApiClient.clearCartSessionByUser(user.getId());
    }

    @Override
    public void setOrderContactInfo(
            final long chatId,
            final User user,
            final long cartSessionId
    ) {
        userModelDataContext.getOrderOnCreateRequest(user.getId()).setShoppingCartSessionId(cartSessionId);
        telegramApiClient.sendMessage(orderMessageGenerator.setOrderContactInfoTitle(chatId));
    }

    @Override
    public void setOrderContactInfoFromProfileHandle(
            final long chatId,
            final User user
    ) {
        preHandle(chatId, user.getId(), CommandData.SET_ORDER_CONTACT_INFO_TITLE.getValue());
        //userModelDataContext.getOrderOnCreateRequest(user.getId()).setShoppingCartSessionId(cartSessionId);
        telegramApiClient.sendMessage(orderMessageGenerator.setOrderContactInfoTitle(chatId));
        postHandle(chatId, user.getId(), CommandData.SET_ORDER_CONTACT_INFO_TITLE.getValue());
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
