package com.fox.shop.notify.bot.service.tg.i;

import com.fox.shop.notify.bot.entity.UploadImageToTelegramEntity;
import com.fox.shop.protocol.ImageByteModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UploadImageToTelegramService {
    Mono<String> getTelegramIdByMainId(long id);

    String existOrUploadAndGetTelegramFileId(
            ImageByteModel image
    );

    List<String> existOrUploadAndGetTelegramFileId(
            List<ImageByteModel> images
    );

    UploadImageToTelegramEntity getByTelegramId(String fileIdOnTelegram);

    String getErrorTelegramFileId();
}
