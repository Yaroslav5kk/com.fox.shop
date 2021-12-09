package com.fox.shop.client.bot.ui.generate.i;

import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;
import com.fox.shop.protocol.ProductModel;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public interface ProductMessageGenerator {
    List<SendPhotoFileIdRequest> productByCategory(
            long chatId,
            long categoryId
    );

    SendMessage afterProductBycategory(
            long chatId,
            long userId
    );

    SendMessage beginBack(
            long chatId
    );

    SendPhotoFileIdRequest product(
            long chatId,
            ProductModel product,
            String fileId
    );
}
