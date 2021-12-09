package com.fox.menu.bot.merchant.service;

import com.fox.menu.bot.merchant.api.client.i.TelegramApiClient;
import com.fox.menu.bot.merchant.entity.UploadImageToTelegramEntity;
import com.fox.menu.bot.merchant.model.type.FileType;
import com.fox.menu.bot.merchant.repository.UploadImageToTelegramRepository;
import com.fox.menu.bot.merchant.service.i.UploadImageToTelegramService;
import com.fox.menu.protocol.ImageByteModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadImageToTelegramServiceImpl implements UploadImageToTelegramService {

    @Value("${telegram.storage.chat-id}")
    private String chatIdToUpload;

    private final TelegramApiClient telegramApiClient;
    private final UploadImageToTelegramRepository uploadedImagesOnTelegramRepository;

    public UploadImageToTelegramServiceImpl(
            final TelegramApiClient telegramApiClient,
            final UploadImageToTelegramRepository uploadedImagesOnTelegramRepository
    ) {
        this.telegramApiClient = telegramApiClient;
        this.uploadedImagesOnTelegramRepository = uploadedImagesOnTelegramRepository;
    }

    @Override
    public UploadImageToTelegramEntity upload(
            final long fileIdBase,
            final MultipartFile multipartFile,
            final FileType fileType,
            final String description
    ) {
        try {
            return sendPhoto(
                    fileIdBase,
                    multipartFile.getBytes(),
                    multipartFile.getOriginalFilename(),
                    description
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new UploadImageToTelegramEntity();
    }

    @Override
    public String existOrUploadAndGetTelegramFileId(
            final ImageByteModel image
    ) {
        if (uploadedImagesOnTelegramRepository.existsById(image.getId()))
            return uploadedImagesOnTelegramRepository.findById(image.getId()).get().getFileIdOnTelegram();
        return sendPhoto(
                image.getId(),
                image.getValue(),
                image.getName(),
                ""
        ).getFileIdOnTelegram();
    }

    @Override
    public List<String> existOrUploadAndGetTelegramFileId(
            final List<ImageByteModel> images
    ) {
        return images.stream().map(this::existOrUploadAndGetTelegramFileId).collect(Collectors.toList());
    }

    @Override
    public UploadImageToTelegramEntity getByTelegramId(final String fileIdOnTelegram) {
        return uploadedImagesOnTelegramRepository.findByFileIdOnTelegram(fileIdOnTelegram);
    }

    @Override
    public String getErrorTelegramFileId() {
        return "error";
    }

    private UploadImageToTelegramEntity sendPhoto(
            long fileIdBase,
            byte[] inputFile,
            String fileName,
            String description
    ) {
        final File file = new File(fileName);
        try {
            FileUtils.writeByteArrayToFile(file, inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final SendPhoto photo = new SendPhoto();
        photo.setChatId(chatIdToUpload);
        photo.setCaption("uploaded file");
        photo.setPhoto(new InputFile(file, fileName));
        final String fileIdOnTelegram = telegramApiClient.sendPhoto(photo).getPhoto().get(0).getFileId();
        return uploadedImagesOnTelegramRepository.save(new UploadImageToTelegramEntity(
                fileIdBase,
                fileIdOnTelegram,
                fileName,
                description
        ));
    }
}
