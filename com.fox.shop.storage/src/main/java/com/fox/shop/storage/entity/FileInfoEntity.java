package com.fox.shop.storage.entity;

import com.fox.shop.storage.types.FileType;
import com.fox.shop.storage.types.StorageProviderType;
import com.fox.shop.storage.types.TelegramHolderType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "file_info")
public class FileInfoEntity implements FatherResource{
  private String id = UUID.randomUUID().toString();
  private String filePath;
  private String telegramFileId;
  private FileType fileType;
  private TelegramHolderType telegramHolderType;
  private StorageProviderType storageProviderType;

  public FileInfoEntity filePath(final String filePath){
    this.filePath = filePath;
    return this;
  }

  public FileInfoEntity telegramFileId(final String telegramFileId){
    this.telegramFileId = telegramFileId;
    return this;
  }

  public FileInfoEntity telegramHolderType(final TelegramHolderType telegramHolderType){
    this.telegramHolderType = telegramHolderType;
    return this;
  }

  public FileInfoEntity storageProviderType(final StorageProviderType storageProviderType){
    this.storageProviderType = storageProviderType;
    return this;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getTelegramFileId() {
    return telegramFileId;
  }

  public void setTelegramFileId(String telegramFileId) {
    this.telegramFileId = telegramFileId;
  }


  public TelegramHolderType getTelegramHolderType() {
    return telegramHolderType;
  }

  public void setTelegramHolderType(TelegramHolderType telegramHolderType) {
    this.telegramHolderType = telegramHolderType;
  }

  public StorageProviderType getStorageProviderType() {
    return storageProviderType;
  }

  public void setStorageProviderType(StorageProviderType storageProviderType) {
    this.storageProviderType = storageProviderType;
  }

  public FileType getFileType() {
    return fileType;
  }

  public void setFileType(FileType fileType) {
    this.fileType = fileType;
  }
}
