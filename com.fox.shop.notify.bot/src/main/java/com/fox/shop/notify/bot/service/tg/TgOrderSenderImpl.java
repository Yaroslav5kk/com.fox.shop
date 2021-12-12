package com.fox.shop.notify.bot.service.tg;

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

    public TgOrderSenderImpl(
            final TgApiClient tgApiClient,
            final UploadImageToTelegramService uploadImageToTelegramService
    ) {
        this.tgApiClient = tgApiClient;
        this.uploadImageToTelegramService = uploadImageToTelegramService;
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
        tgApiClient.sendMessage(tgRequest).map(message ->
                notifyOrderItems(notifyRequest.getItems(), chatId)).subscribe();
        return Mono.empty();
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


    private Mono<Message> notifyOrderItems(
            final List<OrderItemNotifyModel> items,
            final String chatId
    ) {
        for (OrderItemNotifyModel item : items) {
            uploadImageToTelegramService.getTelegramIdByMainId(item.getProductMainImageId())
                    .map(id -> {
                        final SendPhotoFileIdRequest tgRequest = new SendPhotoFileIdRequest();
                        tgRequest.setChatId(chatId);
                        tgRequest.setParseMode("HTML");
                        tgRequest.setPhoto(id);
                        tgRequest.setCaption(OrderViewer.viewOrderItemNotify(item));
                        return tgApiClient.sendPhoto(tgRequest);
                    }).subscribe();
        }
        return Mono.empty();
    }


}
