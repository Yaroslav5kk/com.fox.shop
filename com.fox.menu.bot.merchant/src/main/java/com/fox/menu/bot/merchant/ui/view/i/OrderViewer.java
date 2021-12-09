package com.fox.menu.bot.merchant.ui.view.i;

import com.fox.menu.billing.protocol.model.OrderNotifyModel;

public interface OrderViewer {
    String notifyOrder(OrderNotifyModel model);
}
