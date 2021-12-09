package com.fox.shop.client.bot.ui.view;

import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.bold;

public class StartViewer {


    public static String base() {
        return bold("choose action")
                .toString();
    }

    public static String setPhone() {
        return bold("Please share phone number")
                .toString();
    }

    public static String setName() {
        return bold("Please enter your name")
                .toString();
    }
}
