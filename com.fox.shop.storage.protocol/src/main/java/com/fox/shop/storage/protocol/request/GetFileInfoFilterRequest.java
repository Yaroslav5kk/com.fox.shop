package com.fox.shop.storage.protocol.request;

import com.fox.shop.storage.protocol.types.FileFormat;
import com.fox.shop.storage.protocol.types.FileType;
import com.fox.shop.storage.protocol.types.StorageProviderType;
import com.fox.shop.storage.protocol.types.TelegramHolderType;

import java.util.UUID;

public class GetFileInfoFilterRequest {
  private String id;
  private long baseProductId;
  private long baseProductGroupId;
  private String filePath;
  private String telegramFileId;
  private FileType fileType;
  private FileFormat format;
  private TelegramHolderType telegramHolderType;
  private StorageProviderType storageProviderType;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getBaseProductId() {
    return baseProductId;
  }

  public void setBaseProductId(long baseProductId) {
    this.baseProductId = baseProductId;
  }

  public long getBaseProductGroupId() {
    return baseProductGroupId;
  }

  public void setBaseProductGroupId(long baseProductGroupId) {
    this.baseProductGroupId = baseProductGroupId;
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

  public FileType getFileType() {
    return fileType;
  }

  public void setFileType(FileType fileType) {
    this.fileType = fileType;
  }

  public FileFormat getFormat() {
    return format;
  }

  public void setFormat(FileFormat format) {
    this.format = format;
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
}
