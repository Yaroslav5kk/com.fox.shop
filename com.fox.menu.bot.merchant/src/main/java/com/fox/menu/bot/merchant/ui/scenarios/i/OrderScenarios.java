package com.fox.menu.bot.merchant.ui.scenarios.i;

import com.fox.menu.billing.protocol.model.OrderNotifyModel;

public interface OrderScenarios {
    void notifyOrder(
            OrderNotifyModel orderNotify
    );
}
