package com.fox.shop.client.bot.service.interceptor.i;

import com.fox.shop.client.bot.model.TgIncomingCommandModel;

public interface FatherIncomingInterceptor {

  void interapt(TgIncomingCommandModel update);
}
