package com.fox.shop.client.bot.service.processor.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.model.types.CommandData;
import com.fox.shop.client.bot.model.types.UserDomainState;
import org.springframework.beans.factory.BeanNameAware;

public interface TgCommandProcessorFather  {

  void process(TgIncomingCommandModel incomingCommand);

  CommandData getResponsibleCommand();
}
