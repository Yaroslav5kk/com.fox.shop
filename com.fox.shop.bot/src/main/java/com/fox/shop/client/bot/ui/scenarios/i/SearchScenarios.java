package com.fox.shop.client.bot.ui.scenarios.i;

public interface SearchScenarios extends Scenarios {
  void searchTitle(
      long chatId,
      int userId
  );

  void searchProductTitle(
      long chatId,
      int userId
  );

  void searchProductHandle(
      long chatId,
      int userId,
      String toSearch
  );
}
