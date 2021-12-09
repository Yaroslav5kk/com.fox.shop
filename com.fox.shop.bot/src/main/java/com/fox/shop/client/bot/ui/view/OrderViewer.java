package com.fox.shop.client.bot.ui.view;

import com.fox.shop.ordering.protocol.model.OrderModel;

import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.bold;

public class OrderViewer {

    public static String orderOnName() {
        return bold("Please enter or select name.")
                .toString();
    }

    public static String retryCollectOrderInfoTitle() {
        return bold("Please again retry of enter or reset all")
                .toString();
    }

    public static String successfulMadeOrder() {
        return bold("Your order was successful formed. ")
                .toString();
    }

    public static String setOrderContactInfoTitle() {
        return bold("Please enter your contact info or select 'get from profile'. ")
                .toString();
    }

    public static String verifyOrderTitle(final OrderModel order) {
        return bold("Please verify your order. ")
                .append("\n\n")
                .append(view(order))
                .toString();
    }

    public static String view(final OrderModel order) {
        return bold("total price:   ")
                .append(order.getTotalPrice())
                .append("\n")
                .append(bold("phone:   "))
                .append(order.getPhone())
                .append("\n")
                .toString();
    }
}
