package com.fox.menu.bot.merchant.ui.generator.i;

import com.fox.menu.billing.protocol.model.OrderNotifyModel;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface OrderMessageGenerator {
    SendMessage notifyOrder(
            OrderNotifyModel orderNotify,
            long chatId
    );
}
