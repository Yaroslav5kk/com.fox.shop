package com.fox.shop.notify.bot.service;

import com.fox.shop.notify.bot.entity.UserInfoEntity;
import com.fox.shop.notify.bot.repository.UserInfoRepository;
import com.fox.shop.notify.bot.service.i.OrderNotifyService;
import com.fox.shop.notify.bot.service.tg.i.TgOrderSender;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import com.fox.shop.notify.protocol.response.NotifyResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderNotifyServiceImpl implements OrderNotifyService {

  private final TgOrderSender tgOrderSender;
  private final UserInfoRepository userInfoRepository;

  public OrderNotifyServiceImpl(
      final TgOrderSender tgOrderSender,
      final UserInfoRepository userInfoRepository
  ) {
    this.tgOrderSender = tgOrderSender;
    this.userInfoRepository = userInfoRepository;
  }


  @Override
  public Mono<NotifyResponse> notifyOrder(
      final OrderNotifyRequest request
  ) {
    return userInfoRepository.findById(request.getMerchantId())
        .map(UserInfoEntity::getChatId)
        .doOnNext(chatId -> tgOrderSender.notifyOrder(request, chatId))
        .then(Mono.just(NotifyResponse.ok()));
  }
}
