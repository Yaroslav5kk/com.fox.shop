package com.fox.menu.bot.merchant.ui.generator;

import com.fox.menu.billing.protocol.model.OrderNotifyModel;
import com.fox.menu.bot.merchant.ui.generator.i.OrderMessageGenerator;
import com.fox.menu.bot.merchant.ui.view.i.OrderViewer;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class OrderMessageGeneratorImpl implements OrderMessageGenerator {

    private final OrderViewer orderViewer;

    public OrderMessageGeneratorImpl(
            final OrderViewer orderViewer
    ) {
        this.orderViewer = orderViewer;
    }

    @Override
    public SendMessage notifyOrder(
            final OrderNotifyModel orderNotify,
            final long chatId
    ) {
        final SendMessage result = new SendMessage();
        result.setChatId(chatId);
        result.setText(orderViewer.notifyOrder(orderNotify));
        result.setParseMode("HTML");
        return result;
    }
}
















