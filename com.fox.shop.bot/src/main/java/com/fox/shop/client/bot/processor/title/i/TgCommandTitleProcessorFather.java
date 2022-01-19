package com.fox.shop.client.bot.processor.title.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;

public interface TgCommandTitleProcessorFather {

  void process(TgIncomingCommandModel incomingCommand);

  CommandData getResponsibleCommand();

}
