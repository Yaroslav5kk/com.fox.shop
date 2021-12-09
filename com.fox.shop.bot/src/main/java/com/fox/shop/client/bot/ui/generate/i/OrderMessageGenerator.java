package com.fox.shop.client.bot.ui.generate.i;

import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.ordering.protocol.model.OrderModel;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface OrderMessageGenerator {

    SendPhotoFileIdRequest makeOrderTitle(
            long chatId
    );

    SendMessage setOrderContactInfoTitle(
            long chatId
    );

    SendMessage successfulMadeOrder(
            long chatId,
            long userId
    );
}
