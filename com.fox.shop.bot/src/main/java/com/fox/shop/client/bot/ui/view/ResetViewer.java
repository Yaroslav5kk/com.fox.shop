package com.fox.shop.client.bot.ui.view;

import static com.fox.shop.client.bot.ui.view.HtmlElementGenerator.bold;

public class ResetViewer {

  public static String reset() {
    return bold("Your actions and states are cleaned. ").
        toString();
  }
}
