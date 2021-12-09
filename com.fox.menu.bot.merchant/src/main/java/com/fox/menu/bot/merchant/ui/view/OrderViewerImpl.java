package com.fox.menu.bot.merchant.ui.view;

import com.fox.menu.billing.protocol.model.OrderNotifyModel;
import com.fox.menu.bot.merchant.ui.view.i.OrderViewer;
import org.springframework.stereotype.Component;

import static com.fox.menu.bot.merchant.ui.view.HtmlElementGenerator.bold;

@Component
public class OrderViewerImpl implements OrderViewer {

    @Override
    public String notifyOrder(final OrderNotifyModel model) {
        return bold("New order.")
                .append("\n")
                .append("total price:  ")
                .append(model.getTotalPrice().toString())
                .append("\n")
                .append("phone:  ")
                .append(model.getPhone())
                .append("\n")
                .append("time:  ")
                .append(model.getOrderOnTime())
                .append("\n")
                .append("name:  ")
                .append(model.getOrderOnName())
                .append("\n")
                .append("place:  ")
                .append(model.getPlaceId())
                .append("amount people:  ")
                .append(model.getAmountPeople())
                .toString();
    }
}
