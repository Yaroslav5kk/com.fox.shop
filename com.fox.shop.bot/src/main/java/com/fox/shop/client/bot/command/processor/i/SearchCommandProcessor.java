package com.fox.shop.client.bot.command.processor.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;

public interface SearchCommandProcessor extends CommandProcessor {
  void searchTitle(
       TgIncomingCommandModel incomingCommand
  );

  void searchHandle(
     TgIncomingCommandModel incomingCommand
  );
}
