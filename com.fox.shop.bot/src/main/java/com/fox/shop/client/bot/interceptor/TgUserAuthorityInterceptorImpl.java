package com.fox.shop.client.bot.interceptor;

import com.fox.shop.client.bot.context.i.UserDomainStateContext;
import com.fox.shop.client.bot.interceptor.i.FatherIncomingInterceptor;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class TgUserAuthorityInterceptorImpl implements FatherIncomingInterceptor {

  private final UserDomainStateContext userDomainStateContext;

  public TgUserAuthorityInterceptorImpl(
          final UserDomainStateContext userDomainStateContext
  ) {
    this.userDomainStateContext = userDomainStateContext;
  }

  @Override
  public void interapt(
          final TgIncomingCommandModel update
  ) {
  }
}
























