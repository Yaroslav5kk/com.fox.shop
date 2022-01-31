package com.fox.shop.client.bot.interceptor;

import com.fox.shop.client.bot.api.mediator.TelegramApiMediator;
import com.fox.shop.client.bot.context.i.TgUserSessionContext;
import com.fox.shop.client.bot.interceptor.i.FatherIncomingInterceptor;
import com.fox.shop.client.bot.model.TgIncomingCommandModel;
import com.fox.shop.client.bot.service.i.BannerService;
import com.fox.shop.client.bot.ui.generate.i.BannerMessageGenerator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(3)
public class TgBannerInterceptorImpl implements FatherIncomingInterceptor {

  private final TgUserSessionContext tgUserSessionContext;
  private final BannerMessageGenerator bannerMessageGenerator;
  private final BannerService bannerService;
  private final TelegramApiMediator telegramApiMediator;

  public TgBannerInterceptorImpl(
      final TgUserSessionContext tgUserSessionContext,
      final BannerMessageGenerator bannerMessageGenerator,
      final BannerService bannerService,
      final TelegramApiMediator telegramApiMediator
  ) {
    this.tgUserSessionContext = tgUserSessionContext;
    this.bannerMessageGenerator = bannerMessageGenerator;
    this.bannerService = bannerService;
    this.telegramApiMediator = telegramApiMediator;
  }

  @Override
  public void interapt(
      final TgIncomingCommandModel incomingCommand
  ) {
    bannerService.getByCommand(tgUserSessionContext.getCommand(incomingCommand.getUserId())).ifPresent(banner ->
        telegramApiMediator.addMessage(bannerMessageGenerator.build(banner, incomingCommand.getChatId()))
    );
  }

}










