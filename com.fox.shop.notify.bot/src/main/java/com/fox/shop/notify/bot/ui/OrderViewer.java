package com.fox.shop.notify.bot.ui;

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
}
