package com.fox.shop.client.bot.ui.scenarios.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import org.telegram.telegrambots.meta.api.objects.User;

public interface ProductScenarios extends Scenarios{
    void productByGroup(
        final TgIncomingCommandModel incomingCommand
    );

  void viewProductDescription(
      final TgIncomingCommandModel incomingCommand
  );
}
