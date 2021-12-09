package com.fox.shop.client.bot.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "downloaded_files_on_telegram")
public class UploadImageToTelegramEntity {
    @Id
    @Field(name = "image_id_on_base")
    private long imageIdOnBase;
    @Field(name = "file_id_on_telegram")
    private String fileIdOnTelegram;
    @Field(name = "file_name")
    private String fileName;
    private String description;

    public UploadImageToTelegramEntity() {
    }

    public UploadImageToTelegramEntity(long imageIdOnBase, String fileIdOnTelegram, String fileName, String description) {
        this.imageIdOnBase = imageIdOnBase;
        this.fileIdOnTelegram = fileIdOnTelegram;
        this.fileName = fileName;
        this.description = description;
    }

    public String getFileIdOnTelegram() {
        return fileIdOnTelegram;
    }

    public void setFileIdOnTelegram(String fileIdOnTelegram) {
        this.fileIdOnTelegram = fileIdOnTelegram;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getImageIdOnBase() {
        return imageIdOnBase;
    }

    public void setImageIdOnBase(long imageIdOnBase) {
        this.imageIdOnBase = imageIdOnBase;
    }
}
