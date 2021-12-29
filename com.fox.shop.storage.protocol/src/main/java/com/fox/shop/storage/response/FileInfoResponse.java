package com.fox.shop.storage.response;

import com.fox.shop.storage.types.FileFormat;
import com.fox.shop.storage.types.FileType;
import com.fox.shop.storage.types.StorageProviderType;
import com.fox.shop.storage.types.TelegramHolderType;

public class FileInfoResponse {
  private String id;
  private String filePath;
  private String telegramFileId;
  private FileType fileType;
  private FileFormat format;
  private TelegramHolderType telegramHolderType;
  private StorageProviderType storageProviderType;

  public FileInfoResponse id(final String id){
    this.id = id;
    return this;
  }

  public FileInfoResponse filePath(final String filePath){
    this.filePath = filePath;
    return this;
  }

  public FileInfoResponse telegramFileId(final String telegramFileId){
    this.telegramFileId = telegramFileId;
    return this;
  }

  public FileInfoResponse telegramHolderType(final TelegramHolderType telegramHolderType){
    this.telegramHolderType = telegramHolderType;
    return this;
  }

  public FileInfoResponse storageProviderType(final StorageProviderType storageProviderType){
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

  public FileFormat getFormat() {
    return format;
  }

  public void setFormat(FileFormat format) {
    this.format = format;
  }
}
