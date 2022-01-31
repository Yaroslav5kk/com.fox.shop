package com.fox.shop.client.bot.ui.view;

import com.fox.shop.shoppingcart.protocol.model.full.FullCartItemModel;
import com.fox.shop.shoppingcart.protocol.model.full.FullCartSessionModel;

import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.bold;
import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.italic;

public class ShoppingSessionViewer {

  public static String view(
          final FullCartSessionModel sessionModel,
          final String totalPriceToView
  ) {
    final StringBuilder result = new StringBuilder();
    for (FullCartItemModel item : sessionModel.getItems()) {
      result
              .append("-----")
              .append("\n")
              .append(item.getQuantity())
              .append(" шт.")
              .append("\n")
              .append(italic(item.getProductName()))
              .append("\n");
    }
    result
            .append("-----")
            .append("\n")
            .append(bold("Всього: "))
            .append(italic(totalPriceToView + " " + "&#8372"));
    return result.toString();
  }

  public static String viewSessionItem(
          final FullCartItemModel item,
          final String totalPriceToView
  ) {
    return bold("Name: ").
            append(italic(item.getProductName())).
            append("\n").
            append(bold("Quantity: ")).
            append(italic(String.valueOf(item.getQuantity()))).
            append("\n").
            append(bold("Price: ")).
            append(italic(totalPriceToView + "  " + "&#8372")).
            append("\n").toString();
  }

  public static String setNewCartItemQuantityTitle() {
    return bold("Відправте будь яку кількість або виберіть із запропонованих. ☺").toString();
  }

  public static String beginBack() {
    return bold("Available actions.").toString();
  }

  public static String successAddToCart() {
    return bold("Product was successful added").
            toString();
  }

  public static String successClearSession() {
    return bold("Корзина була успішно очищена! \uD83D\uDE44 ").
            toString();
  }

  public static String emptySession() {
    return bold("На привеликий жаль, корзина покищо пуста. \uD83D\uDE31").
            toString();
  }

  public static String inputQuantityOnCartItem() {
    return italic("Please select amount items to add").
            toString();
  }

  public static String switchOrClearSession() {
    return italic("You have active session.")
            .append("\n")
            .append("\n")
            .append("you can switch or clear session.")
            .toString();
  }
}
