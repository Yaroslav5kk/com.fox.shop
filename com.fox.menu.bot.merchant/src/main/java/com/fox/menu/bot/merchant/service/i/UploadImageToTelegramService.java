package com.fox.menu.bot.merchant.service.i;

import com.fox.menu.bot.merchant.entity.UploadImageToTelegramEntity;
import com.fox.menu.bot.merchant.model.type.FileType;
import com.fox.menu.protocol.ImageByteModel;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface UploadImageToTelegramService {
    UploadImageToTelegramEntity upload(
            long fileIdBase,
            MultipartFile multipartFile,
            FileType fileType,
            String description
    );

    String existOrUploadAndGetTelegramFileId(
            ImageByteModel image
    );

    List<String> existOrUploadAndGetTelegramFileId(
            List<ImageByteModel> images
    );

    UploadImageToTelegramEntity getByTelegramId(String fileIdOnTelegram);

    String getErrorTelegramFileId();

}
