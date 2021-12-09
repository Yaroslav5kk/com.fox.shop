package com.fox.shop.notify.bot.service.tg;

import com.fox.shop.notify.bot.api.client.i.TgApiClient;
import com.fox.shop.notify.bot.repository.UserInfoRepository;
import com.fox.shop.notify.bot.service.tg.i.TgOrderSender;
import com.fox.shop.notify.bot.ui.OrderViewer;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import reactor.core.publisher.Mono;

@Component
public class TgOrderSenderImpl implements TgOrderSender {

  private final TgApiClient tgApiClient;

  public TgOrderSenderImpl(
      final TgApiClient tgApiClient
  ) {
    this.tgApiClient = tgApiClient;
  }

  @Override
  public Mono<Void> notifyOrder(
      final OrderNotifyRequest notifyRequest,
      final String chatId
  ) {
    final SendMessage tgRequest = new SendMessage();
    tgRequest.setChatId(chatId);
    tgRequest.setParseMode("HTML");
    tgRequest.setText(OrderViewer.viewOrderNotify(notifyRequest));
    return tgApiClient.sendMessage(tgRequest).then();
  }


}
