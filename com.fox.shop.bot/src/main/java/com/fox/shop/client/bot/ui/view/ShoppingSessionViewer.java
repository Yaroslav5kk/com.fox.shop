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
        final StringBuilder result = bold("Total price: ").
                append(italic(totalPriceToView+" "+"&#8372"));
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
        return bold("Please enter new item quantity or 0 to delete.").toString();
    }

    public static String beginBack() {
        return bold("Available actions.").toString();
    }

    public static String successAddToCart() {
        return bold("Product was successful added").
                toString();
    }

    public static String successClearSession() {
        return bold("Cart are success cleaned. ").
                toString();
    }

    public static String emptySession() {
        return bold("Your cart are empty. ").
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
