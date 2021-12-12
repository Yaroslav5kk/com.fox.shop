package com.fox.shop.notify.bot.ui;

import com.fox.shop.notify.protocol.OrderItemNotifyModel;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;

import static com.fox.shop.notify.bot.ui.HtmlElementGenerator.bold;
import static com.fox.shop.notify.bot.ui.HtmlElementGenerator.italic;

public class OrderViewer {

    public static String viewOrderNotify(
            final OrderNotifyRequest notifyRequest
    ) {
        return bold("New order with id: ")
                .append(italic(notifyRequest.getOrderId()))
                .append("\n")
                .append(bold("First name: "))
                .append(italic(notifyRequest.getFirstName()))
                .append("\n")
                .append(bold("Last name: "))
                .append(italic(notifyRequest.getLastName()))
                .append("\n")
                .append(bold("Phone: "))
                .append(italic(notifyRequest.getPhone()))
                .append("\n")
                .append(bold("telegram username: "))
                .append(italic(notifyRequest.getTelegramUsername()))
                .append("\n")
                .toString();
    }

    public static String viewOrderItemNotify(final OrderItemNotifyModel orderItem) {
        return bold("Product id: ")
                .append(italic(orderItem.getProductId()))
                .append("\n")
                .append(bold("Name: "))
                .append(italic(orderItem.getProductName()))
                .append("\n")
                .append(bold("Price at one: "))
                .append(italic(orderItem.getPriceAtOne() + " ₴"))
                .append("\n")
                .append(bold("Quantity: "))
                .append(italic(orderItem.getQuantity()))
                .toString();
    }

    public static String split(){
        return "==================================" +
                "=====================================" +
                "===========================================" +
                "===============================================" +
                "=========================================================" +
                "================================================================" +
                "===================================================================";
    }
}
