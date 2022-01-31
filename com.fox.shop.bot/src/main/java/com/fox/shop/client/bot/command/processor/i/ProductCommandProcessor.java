package com.fox.shop.client.bot.command.processor.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;

public interface ProductCommandProcessor extends CommandProcessor {
    void productByGroup(
        final TgIncomingCommandModel incomingCommand
    );

  void viewProductDescription(
      final TgIncomingCommandModel incomingCommand
  );
}
