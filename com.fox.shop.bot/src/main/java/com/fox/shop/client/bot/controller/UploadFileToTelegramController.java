package com.fox.shop.client.bot.controller;

import com.fox.shop.client.bot.entity.UploadImageToTelegramEntity;
import com.fox.shop.client.bot.model.types.FileType;
import com.fox.shop.client.bot.service.i.UploadImageToTelegramService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/upload")
public class UploadFileToTelegramController {

    private final UploadImageToTelegramService uploadImageToTelegramService;

    public UploadFileToTelegramController(
            final UploadImageToTelegramService uploadImageToTelegramService
    ) {
        this.uploadImageToTelegramService = uploadImageToTelegramService;
    }

    @PostMapping("file-to-telegram")
    public ResponseEntity<UploadImageToTelegramEntity> uploadToTelegram(
            @RequestParam final long fileIdBase,
            @RequestParam final MultipartFile file,
            @RequestParam final String fileType,
            @RequestParam final String description
    ) {
        return ResponseEntity.ok(uploadImageToTelegramService.upload(
                fileIdBase,
                file,
                FileType.fromName(fileType),
                description
        ));
    }
}
