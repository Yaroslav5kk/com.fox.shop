package com.fox.menu.bot.merchant.ui.scenarios;

import com.fox.menu.billing.protocol.model.OrderNotifyModel;
import com.fox.menu.bot.merchant.api.client.i.TelegramApiClient;
import com.fox.menu.bot.merchant.repository.WaiterInfoRepository;
import com.fox.menu.bot.merchant.ui.generator.i.OrderMessageGenerator;
import com.fox.menu.bot.merchant.ui.scenarios.i.OrderScenarios;
import org.springframework.stereotype.Component;

@Component
public class OrderScenariosImpl implements OrderScenarios {

    private final OrderMessageGenerator orderMessageGenerator;
    private final WaiterInfoRepository waiterInfoRepository;
    private final TelegramApiClient telegramApiClient;

    public OrderScenariosImpl(
            final OrderMessageGenerator orderMessageGenerator,
            final WaiterInfoRepository waiterInfoRepository,
            final TelegramApiClient telegramApiClient
    ) {
        this.orderMessageGenerator = orderMessageGenerator;
        this.waiterInfoRepository = waiterInfoRepository;
        this.telegramApiClient = telegramApiClient;
    }

    @Override
    public void notifyOrder(
            final OrderNotifyModel orderNotify
    ) {
        telegramApiClient.sendMessage(orderMessageGenerator.notifyOrder(
                orderNotify,
                waiterInfoRepository.findById(orderNotify.getUserId()).get().getChatId()
        ));
    }
}





















