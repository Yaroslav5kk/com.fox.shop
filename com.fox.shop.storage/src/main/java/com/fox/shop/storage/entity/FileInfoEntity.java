package com.fox.shop.storage.entity;

import com.fox.shop.storage.protocol.types.FileFormat;
import com.fox.shop.storage.protocol.types.FileType;
import com.fox.shop.storage.protocol.types.StorageProviderType;
import com.fox.shop.storage.protocol.types.TelegramHolderType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "file_info")
public class FileInfoEntity implements FatherResource {
  @Id
  @Indexed(unique = true)
  private String id = UUID.randomUUID().toString();
  private int baseId;
  private long baseProductId;
  private long baseProductGroupId;
  @Indexed(unique = true)
  private String filePath;
  private String telegramFileId;
  private FileType fileType;
  private FileFormat format;
  private TelegramHolderType telegramHolderType;
  private StorageProviderType storageProviderType;

  public FileInfoEntity filePath(final String filePath) {
    this.filePath = filePath;
    return this;
  }

  public FileInfoEntity baseProductId(final long baseProductId) {
    this.baseProductId = baseProductId;
    return this;
  }

  public FileInfoEntity baseProductGroupId(final long baseProductGroupId) {
    this.baseProductGroupId = baseProductGroupId;
    return this;
  }

  public FileInfoEntity telegramFileId(final String telegramFileId) {
    this.telegramFileId = telegramFileId;
    return this;
  }

  public FileInfoEntity telegramHolderType(final TelegramHolderType telegramHolderType) {
    this.telegramHolderType = telegramHolderType;
    return this;
  }

  public FileInfoEntity fileType(final FileType fileType) {
    this.fileType = fileType;
    return this;
  }

  public FileInfoEntity setAndParseFromNameFormat(final String fileName) {
    String[] split = fileName.split("\\.");
    this.format = FileFormat.fromFormatValue(split[split.length - 1]);
    return this;
  }

  public FileInfoEntity storageProviderType(final StorageProviderType storageProviderType) {
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

  public int getBaseId() {
    return baseId;
  }

  public void setBaseId(int baseId) {
    this.baseId = baseId;
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
}
