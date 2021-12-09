package com.fox.shop.client.bot.ui.view;

import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.bold;

public class PaginationViewer {

    public static String pagination() {
        return bold("You can change the page number.").toString();
    }
}
