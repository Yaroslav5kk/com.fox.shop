package com.fox.shop.client.bot.service.i;

import com.fox.shop.client.bot.entity.UploadImageToTelegramEntity;
import com.fox.shop.client.bot.model.types.FileType;
import com.fox.shop.protocol.ImageByteModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadImageToTelegramService {

    UploadImageToTelegramEntity upload(
            long fileIdBase,
            MultipartFile multipartFile,
            FileType fileType,
            String description
    );

    String getTelegramIdByMainId(long id);

    String existOrUploadAndGetTelegramFileId(
            ImageByteModel image
    );

    List<String> existOrUploadAndGetTelegramFileId(
            List<ImageByteModel> images
    );

    UploadImageToTelegramEntity getByTelegramId(String fileIdOnTelegram);

    String getErrorTelegramFileId();
}
