package com.fox.shop.client.bot.interceptor;

import com.fox.shop.client.bot.interceptor.i.FatherIncomingInterceptor;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class TgUserAuthorityInterceptorImpl implements FatherIncomingInterceptor {

  private final UserCommandStateContext userCommandStateContext;

  public TgUserAuthorityInterceptorImpl(
      final UserCommandStateContext userCommandStateContext
  ) {
    this.userCommandStateContext = userCommandStateContext;
  }

  @Override
  public void interapt(
      final TgIncomingCommandModel update
  ) {
  }
}
























