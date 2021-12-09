package com.fox.shop.client.bot.ui.view;

import com.fox.shop.protocol.CategoryModel;

import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.bold;
import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.italic;

public class CategoryViewer {

    public static String view(final CategoryModel category) {
        return bold("Name: ")
                .append(italic(category.getName()))
                .append("\n")
                .append(bold("Description: "))
                .append(italic(category.getDescription()))
                .toString();
    }

    public static String afterAllCategory() {
        return bold("Available actions.").toString();
    }
}
