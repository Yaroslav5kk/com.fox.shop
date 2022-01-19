package com.fox.shop.client.bot.processor.handle.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;

public interface TgCommandProcessorFather  {

  void process(TgIncomingCommandModel incomingCommand);

  CommandData getResponsibleCommand();
}
