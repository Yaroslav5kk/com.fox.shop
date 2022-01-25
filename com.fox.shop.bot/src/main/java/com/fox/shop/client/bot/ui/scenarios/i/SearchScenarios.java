package com.fox.shop.client.bot.ui.scenarios.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;

public interface SearchScenarios extends Scenarios {
  void searchTitle(
       TgIncomingCommandModel incomingCommand
  );

  void searchHandle(
     TgIncomingCommandModel incomingCommand
  );
}
