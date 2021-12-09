package com.fox.shop.client.bot.ui.scenarios.i;

public interface ResetScenarios extends Scenarios{
  void reset(
      long chatId,
      int userId
  );
}
