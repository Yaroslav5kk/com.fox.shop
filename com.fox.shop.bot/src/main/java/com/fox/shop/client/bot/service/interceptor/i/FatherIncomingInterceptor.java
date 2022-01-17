package com.fox.shop.client.bot.service.interceptor.i;

import com.fox.shop.client.bot.service.interceptor.model.TgCommandInterceptorModel;

public interface FatherIncomingInterceptor {

  void interapt(TgCommandInterceptorModel update);
}
