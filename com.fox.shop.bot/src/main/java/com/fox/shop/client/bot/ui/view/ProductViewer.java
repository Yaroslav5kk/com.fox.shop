package com.fox.shop.client.bot.ui.view;

import com.fox.shop.protocol.ProductModel;

import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.bold;
import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.italic;

public class ProductViewer {

    public static String view(
            final ProductModel productModel,
            final String priceToView
    ) {
        return bold("Name: ")
                .append(italic(productModel.getName()))
                .append("\n")
                .append(bold("Price: "))
                .append(italic(priceToView+" "+"&#8372"))
                .toString();
    }

    public static String viewWithDescription(
        final ProductModel productModel,
        final String priceToView
    ) {
        return bold("Name: ")
            .append(italic(productModel.getName()))
            .append("\n")
            .append(bold("Description: "))
            .append(italic(productModel.getDescription()))
            .append("\n")
            .append(bold("Price: "))
            .append(italic(priceToView+" "+"&#8372"))
            .toString();
    }

    public static String viewWithQuantity(
            final ProductModel productModel,
            final int quantity,
            final String priceToView
    ) {
        return bold("Name: ")
                .append(italic(productModel.getName()))
                .append("\n")
                .append(bold("Description: "))
                .append(italic(productModel.getDescription()))
                .append("\n")
                .append(bold("Price: "))
                .append(italic(priceToView+" "+"&#8372"))
                .append("\n")
                .append(bold("Quantity: "))
                .append(italic(String.valueOf(quantity)))
                .toString();
    }


    public static String afterProductBycategory() {
        return bold("Available actions.").toString();
    }
}
