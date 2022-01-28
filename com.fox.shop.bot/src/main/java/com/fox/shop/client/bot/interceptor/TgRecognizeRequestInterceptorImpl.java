package com.fox.shop.client.bot.interceptor;

import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.interceptor.i.FatherIncomingInterceptor;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class TgRecognizeRequestInterceptorImpl implements FatherIncomingInterceptor {

  private final TgUserSessionContext tgUserSessionContext;

  public TgRecognizeRequestInterceptorImpl(
      final TgUserSessionContext tgUserSessionContext
  ) {
    this.tgUserSessionContext = tgUserSessionContext;
  }

  @Override
  public void interapt(
      final TgIncomingCommandModel incomingCommand
  ) {
    incomingCommand.getCommand().ifPresent(commandData ->
        tgUserSessionContext.setupCommand(incomingCommand.getUserId(), commandData)
    );
  }
}
























