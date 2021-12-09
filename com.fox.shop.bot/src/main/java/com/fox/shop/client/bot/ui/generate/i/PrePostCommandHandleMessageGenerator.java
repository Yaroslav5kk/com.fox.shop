package com.fox.shop.client.bot.ui.generate.i;

import com.fox.shop.client.bot.model.request.SendPhotoFileIdRequest;

public interface PrePostCommandHandleMessageGenerator {

    SendPhotoFileIdRequest preHandle(
            long chatId,
            int userId,
            String command
    );

    SendPhotoFileIdRequest postHandle(
            long chatId,
            int userId,
            String command
    );
}
