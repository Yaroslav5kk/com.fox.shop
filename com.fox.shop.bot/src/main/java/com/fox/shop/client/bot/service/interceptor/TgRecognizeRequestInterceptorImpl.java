package com.fox.shop.client.bot.service.interceptor;

import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.service.interceptor.i.FatherIncomingInterceptor;
import com.fox.shop.client.bot.service.interceptor.model.TgCommandInterceptorModel;
import com.fox.shop.client.bot.model.types.UserDomainState;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class TgRecognizeRequestInterceptorImpl implements FatherIncomingInterceptor {

  private final UserDomainStateContext userDomainStateContext;

  public TgRecognizeRequestInterceptorImpl(
          final UserDomainStateContext userDomainStateContext
  ) {
    this.userDomainStateContext = userDomainStateContext;
  }

  @Override
  public void interapt(
          final TgCommandInterceptorModel update
  ) {
    update.getCommand().ifPresent(commandData ->
            userDomainStateContext.setup(update.getUserId(), UserDomainState.fromCommand(commandData))
    );
  }
}
























