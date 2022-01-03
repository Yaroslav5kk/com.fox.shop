package com.fox.shop.notify.bot.service.tg;

import com.fox.shop.notify.bot.api.client.i.StorageApiClient;
import com.fox.shop.notify.bot.api.client.i.TgApiClient;
import com.fox.shop.notify.bot.model.tg.request.SendPhotoFileIdRequest;
import com.fox.shop.notify.bot.service.tg.i.TgOrderSender;
import com.fox.shop.notify.bot.service.tg.i.UploadImageToTelegramService;
import com.fox.shop.notify.bot.ui.OrderViewer;
import com.fox.shop.notify.protocol.OrderItemNotifyModel;
import com.fox.shop.notify.protocol.request.OrderNotifyRequest;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class TgOrderSenderImpl implements TgOrderSender {

  private final TgApiClient tgApiClient;
  private final UploadImageToTelegramService uploadImageToTelegramService;
  private final StorageApiClient storageApiClient;

  public TgOrderSenderImpl(
          final TgApiClient tgApiClient,
          final UploadImageToTelegramService uploadImageToTelegramService,
          final StorageApiClient storageApiClient
  ) {
    this.tgApiClient = tgApiClient;
    this.uploadImageToTelegramService = uploadImageToTelegramService;
    this.storageApiClient = storageApiClient;
  }

  @Override
  public Mono<Message> notifyOrder(
          final OrderNotifyRequest notifyRequest,
          final String chatId
  ) {
    sendSplitMessage(chatId);
    final SendMessage tgRequest = new SendMessage();
    tgRequest.setChatId(chatId);
    tgRequest.setParseMode("HTML");
    tgRequest.setText(OrderViewer.viewOrderNotify(notifyRequest));
    final Mono<Message> messageMono = tgApiClient.sendMessage(tgRequest)
            .doOnSuccess(message -> notifyOrderItems(notifyRequest.getItems(), chatId));
    messageMono
            .subscribe();
    return messageMono;
  }

  private void sendSplitMessage(
          final String chatId
  ) {
    final SendMessage tgRequest = new SendMessage();
    tgRequest.setChatId(chatId);
    tgRequest.setParseMode("HTML");
    tgRequest.setText(OrderViewer.split());
    tgApiClient.sendMessage(tgRequest).block();
  }


  private void notifyOrderItems(
          final List<OrderItemNotifyModel> items,
          final String chatId
  ) {
    for (OrderItemNotifyModel item : items) {
      storageApiClient.getTelegramIdByBaseId(item.getProductMainImageId())
              .doOnSuccess(id -> {
                final SendPhotoFileIdRequest tgRequest = new SendPhotoFileIdRequest();
                tgRequest.setChatId(chatId);
                tgRequest.setParseMode("HTML");
                tgRequest.setPhoto(id);
                tgRequest.setCaption(OrderViewer.viewOrderItemNotify(item));
                tgApiClient.sendPhoto(tgRequest).subscribe();
              }).subscribe();
    }
  }


}


























