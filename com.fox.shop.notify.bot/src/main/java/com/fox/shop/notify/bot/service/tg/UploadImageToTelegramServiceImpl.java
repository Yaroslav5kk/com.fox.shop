package com.fox.shop.notify.bot.service.tg;

import com.fox.shop.notify.bot.api.client.i.TgApiClient;
import com.fox.shop.notify.bot.entity.UploadImageToTelegramEntity;
import com.fox.shop.notify.bot.repository.UploadImageToTelegramRepository;
import com.fox.shop.notify.bot.service.tg.i.UploadImageToTelegramService;
import com.fox.shop.protocol.ImageByteModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadImageToTelegramServiceImpl implements UploadImageToTelegramService {

    @Value("${telegram.storage.chat-id}")
    private String chatIdToUpload;
    @Value("${telegram.storage.error.file-id-on-telegram}")
    private String errorProductFileIdOnTelegram;

    private final TgApiClient tgApiClient;
    private final UploadImageToTelegramRepository uploadedImagesOnTelegramRepository;

    public UploadImageToTelegramServiceImpl(
            final TgApiClient tgApiClient,
            final UploadImageToTelegramRepository uploadedImagesOnTelegramRepository
    ) {
        this.tgApiClient = tgApiClient;
        this.uploadedImagesOnTelegramRepository = uploadedImagesOnTelegramRepository;
    }

    @Override
    public Mono<String> getTelegramIdByMainId(final long id) {
        return uploadedImagesOnTelegramRepository.findById(id).map(UploadImageToTelegramEntity::getFileIdOnTelegram);
    }

    @Override
    public String existOrUploadAndGetTelegramFileId(
            final ImageByteModel image
    ) {
        if (uploadedImagesOnTelegramRepository.existsById(image.getId()).block())
            return uploadedImagesOnTelegramRepository.findById(image.getId()).block().getFileIdOnTelegram();
        return null;/*sendPhoto(
                image.getId(),
                image.getValue(),
                image.getName(),
                ""
        ).getFileIdOnTelegram();*/
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

    /*private UploadImageToTelegramEntity sendPhoto(
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
        final String fileIdOnTelegram = tgApiClient.sendPhoto(photo).getPhoto().get(0).getFileId();
        return uploadedImagesOnTelegramRepository.save(new UploadImageToTelegramEntity(
                fileIdBase,
                fileIdOnTelegram,
                fileName,
                description
        ));
    }*/
}
