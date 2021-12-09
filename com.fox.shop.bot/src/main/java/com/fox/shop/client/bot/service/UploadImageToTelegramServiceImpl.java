package com.fox.shop.client.bot.service;

import com.fox.shop.client.bot.api.client.i.TelegramApiClient;
import com.fox.shop.client.bot.entity.UploadImageToTelegramEntity;
import com.fox.shop.client.bot.model.types.FileType;
import com.fox.shop.client.bot.repository.UploadImageToTelegramRepository;
import com.fox.shop.client.bot.service.i.UploadImageToTelegramService;
import com.fox.shop.protocol.ImageByteModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UploadImageToTelegramServiceImpl implements UploadImageToTelegramService {

    @Value("${telegram.storage.chat-id}")
    private String chatIdToUpload;
    @Value("${telegram.storage.error.product.file-id-on-telegram}")
    private String errorProductFileIdOnTelegram;

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
    public String getTelegramIdByMainId(final long id) {
        final Optional<UploadImageToTelegramEntity> fromDb = uploadedImagesOnTelegramRepository.findById(id);

        return fromDb.isPresent()
                ? fromDb.get().getFileIdOnTelegram()
                : errorProductFileIdOnTelegram;
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
        return errorProductFileIdOnTelegram;
    }

    private UploadImageToTelegramEntity sendPhoto(
            final long fileIdBase,
            final byte[] inputFile,
            final String fileName,
            final String description
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
